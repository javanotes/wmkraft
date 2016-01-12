/* ============================================================================
*
* FILE: ProductDimension.java
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

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ProductDimensionSupport {

  public ProductDimensionSupport() {
    super();
  }

  public ProductDimensionSupport(Double length, Double breadth, Double height,
      Double weight) {
    super();
    this.length = length;
    this.breadth = breadth;
    this.height = height;
    this.weight = weight;
  }

  private Double length,breadth,height,weight;
}
