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
}
