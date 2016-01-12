/* ============================================================================
*
* FILE: ProductBundlePK.java
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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLine;

import lombok.Data;
@Data
@Embeddable
public class ProductBundlePK implements Serializable{

  public ProductBundlePK()
  {
    
  }
  public ProductBundlePK(Long id)
  {
    this.id = id;
  }
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Column(name = "PROD_BUNDLE_ID")
  private Long id;
  @OneToOne
  @JoinColumn(name = "PROD_TYP_ID", referencedColumnName = "PROD_TYP_ID")
  private ProductLine product;
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductBundlePK other = (ProductBundlePK) obj;
    if (id == null) {
      //if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (product == null) {
      //if (other.product != null)
        return false;
    } else if (!product.equals(other.product))
      return false;
    return true;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());
    result = prime * result + ((product == null) ? super.hashCode() : product.hashCode());
    return result;
  }
}
