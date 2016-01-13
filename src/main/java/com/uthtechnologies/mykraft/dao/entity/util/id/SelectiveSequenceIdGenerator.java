/* ============================================================================
*
* FILE: SelectiveSequenceIdGenerator.java
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
package com.uthtechnologies.mykraft.dao.entity.util.id;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.uthtechnologies.mykraft.dao.entity.catalog.xref.ProductBundle;

public class SelectiveSequenceIdGenerator extends SequenceStyleGenerator {

  public SelectiveSequenceIdGenerator()
  {
    super();
  }
  public Serializable generate(SessionImplementor session, Object object) throws HibernateException
  {
    if(object instanceof ProductBundle)
    {
      ProductBundle pb = (ProductBundle) object;
      if(pb.getBundleId() != null)
      {
        if(pb.getBundleId().getId() == null)
          return super.generate(session, object);
        else
          return pb.getBundleId();
      }
        
    }
    return super.generate(session, object);
    
  }
}
