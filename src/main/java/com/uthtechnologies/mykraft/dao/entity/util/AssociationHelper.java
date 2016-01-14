/* ============================================================================
*
* FILE: AssociationHelper.java
*
* MODULE DESCRIPTION:
* See class description
*
* Copyright (C) 2015 
*
* All rights reserved
*
* ============================================================================
*/
package com.uthtechnologies.mykraft.dao.entity.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class AssociationHelper {
  
  @SuppressWarnings({ "unchecked", "unused" })
  private void updateEntity(Object entity)
  {
    //this is convention over configuration
    String repoClass = "com.uthtechnologies.mykraft.dao."+entity.getClass().getSimpleName()+"Repo";
    try 
    {
      JpaRepository<Object, ?> jpaRepo = (JpaRepository<Object, ?>) ctx.getBean(Class.forName(repoClass));
      jpaRepo.saveAndFlush(entity);
      log.debug("Entity saved "+entity);
    } 
    catch (ClassNotFoundException e) {
      log.error("Repository implementation not found. Expected class ["+repoClass+"]", e);
    }
    catch(BeansException b)
    {
      log.error("Repository Bean not configured. Expected type ["+repoClass+"]", b);
    }
    catch(Exception b)
    {
      log.error("Error while trying to update a child entity of type- "+entity.getClass(), b);
    }
    
  }
  @Autowired
  private ApplicationContext ctx;
  /**
   * Removes OneToMany and OneToOne foreign key bi-directional dependencies
   * @param entity
   */
  public void removeForeignKeyAssociations(final Object entity)
  {
    ReflectionUtils.doWithFields(entity.getClass(), new FieldCallback() {
      
      @Override
      public void doWith(Field field)
          throws IllegalArgumentException, IllegalAccessException {
        if(Collection.class.isAssignableFrom(field.getType()))
        {
          Collection<?> c = (Collection<?>) FieldUtils.readField(field, entity, true);
          
          if (field.isAnnotationPresent(OneToMany.class)) 
          {
            OneToMany otm = field.getAnnotation(OneToMany.class);
            for (Iterator<?> iter = c.iterator(); iter.hasNext();) {
              Object child = iter.next();
              removeForeignKeyAssociations(child);
              String mb = otm.mappedBy();
              char c0 = Character.toUpperCase(mb.charAt(0));
              mb = c0 + mb.substring(1);
              try {
                //set null
                MethodUtils.invokeExactMethod(child, "set" + mb,
                    new Object[] { null }, new Class[] { entity.getClass() });
                iter.remove();
                
              } catch (NoSuchMethodException | InvocationTargetException e) {
                log.warn("Unable to set null parent association", e);
              }
            } 
          }
          
        }
        else
        {
          //OneToOne
          if(field.isAnnotationPresent(OneToOne.class))
          {
            Object child = FieldUtils.readField(field, entity, true);
            removeForeignKeyAssociations(child);
            String mb = field.getAnnotation(OneToOne.class).mappedBy();
            char c0 = Character.toUpperCase(mb.charAt(0));
            mb = c0 + mb.substring(1);
            try {
              //set null
              MethodUtils.invokeExactMethod(child, "set" + mb,
                  new Object[] { null }, new Class[] { entity.getClass() });
              FieldUtils.writeField(field, entity, null, true);
              

            } catch (NoSuchMethodException | InvocationTargetException e) {
              log.warn("Unable to set null parent association", e);
            }
          }
        }
        
      }
    }, new FieldFilter() {
      
      @Override
      public boolean matches(Field field) {
        return field.isAnnotationPresent(OneToMany.class)
            || (field.isAnnotationPresent(OneToOne.class) && StringUtils
                .hasText(field.getAnnotation(OneToOne.class).mappedBy()));
      }
    });
  }

}
