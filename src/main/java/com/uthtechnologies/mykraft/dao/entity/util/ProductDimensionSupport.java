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

  private Double length,breadth,height,weight;
}
