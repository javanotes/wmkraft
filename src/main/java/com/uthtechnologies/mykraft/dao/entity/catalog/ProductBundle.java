/* ============================================================================
*
* FILE: ProductBundle.java
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
package com.uthtechnologies.mykraft.dao.entity.catalog;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.uthtechnologies.mykraft.dao.entity.util.ProductBundlePK;

import lombok.Data;

@Entity
@Data
@Table(name = "WMK_PRODUCT_BUNDLE")
public class ProductBundle {
  @EmbeddedId
  @GeneratedValue(generator = "custom-seq")
  @GenericGenerator(name = "custom-seq", strategy = "com.uthtechnologies.mykraft.dao.entity.util.id.SelectiveSequenceIdGenerator")
  private ProductBundlePK bundleId;
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductBundle other = (ProductBundle) obj;
    if (bundleId == null) {
      //if (other.id != null)
        return false;
    } else if (!bundleId.equals(other.bundleId))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bundleId == null) ? super.hashCode() : bundleId.hashCode());
    return result;
  }
}
