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
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.uthtechnologies.mykraft.dao.entity.catalog.vendor.Product;
/**
 * @deprecated - Using DB trigger to generate product code
 */
public class ProductIdGenerator extends SequenceStyleGenerator {

  private static ProductIdGenerator instance;
  private final AtomicLong id;
  private ProductIdGenerator()
  {
    super();
    id = new AtomicLong(System.currentTimeMillis());
  }
  public static ProductIdGenerator instance()
  {
    if(instance == null)
    {
      synchronized (ProductIdGenerator.class) {
        if(instance == null)
        {
          instance = new ProductIdGenerator();
        }
        
      }
    }
    return instance;
    
  }
  /**
   * 
   * @param pb
   * @param maxNumLen
   * @return
   */
  public String getNextId(Product pb, int maxNumLen)
  {
    
    StringBuilder s = new StringBuilder();
    if(pb.getVendor() == null)
      throw new IllegalStateException("Vendor mapping not done to product");
    s.append(StringUtils.rightPad(abbreviateMiddle(pb.getVendor().getUserName()), 4, 'X').toUpperCase());
    if(pb.getProductLine() == null)
      throw new IllegalStateException("ProductLine mapping not done to product");
    s.append(StringUtils.rightPad(abbreviateMiddle(pb.getProductLine().getProdTypeLabel()), 4, 'X').toUpperCase());
    s.append(StringUtils.leftPad(StringUtils.right(String.valueOf(id.incrementAndGet()), maxNumLen), maxNumLen, '0'));
    return s.toString();
  }
  public static String abbreviateMiddle(String userName) {
    return StringUtils.substring(userName, 0, 2) + StringUtils.right(userName, 2);
  }
  public Serializable generate(SessionImplementor session, Object object) throws HibernateException
  {
    if(object instanceof Product)
    {
      return getNextId((Product) object, 12);
        
    }
    return super.generate(session, object);
    
  }
}
