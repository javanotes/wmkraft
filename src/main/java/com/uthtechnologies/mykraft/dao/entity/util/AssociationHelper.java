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

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;

public class AssociationHelper {
  private AssociationHelper(){}
  public static void removeOneToManyAssociations(final Object entity)
  {
    ReflectionUtils.doWithFields(entity.getClass(), new FieldCallback() {
      
      @Override
      public void doWith(Field field)
          throws IllegalArgumentException, IllegalAccessException {
        if(Collection.class.isAssignableFrom(field.getType()))
        {
          Collection<?> c = (Collection<?>) FieldUtils.readField(field, entity, true);
          OneToMany otm = field.getAnnotation(OneToMany.class);
          for(Iterator<?> iter = c.iterator(); iter.hasNext();)
          {
            Object child = iter.next();
            removeOneToManyAssociations(child);
            String mb = otm.mappedBy();
            char c0 = Character.toUpperCase(mb.charAt(0));
            mb = c0 + mb.substring(1);
            try 
            {
              //set null
              MethodUtils.invokeExactMethod(child, "set"+mb, new Object[]{null}, new Class[]{entity.getClass()});
              iter.remove();
              //System.out.println("Removed..."+child);
            } catch (NoSuchMethodException | InvocationTargetException e) {
              e.printStackTrace();
            }
          }
        }
        
      }
    }, new FieldFilter() {
      
      @Override
      public boolean matches(Field field) {
        return field.isAnnotationPresent(OneToMany.class);
      }
    });
  }

}
