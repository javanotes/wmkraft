/* ============================================================================
*
* FILE: ProductCosting.java
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

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ProductPricingSupport {

  public ProductPricingSupport()
  {
    
  }
  public ProductPricingSupport(Double itemPrice, int itemQty)
  {
    productPrice = itemPrice*itemQty;
  }
  @Column(name = "GROSS_PRICE")
  private Double productPrice = 0.0;
  @Column(name = "OP_MARGIN")
  private Double operatingMarginRate = 0.0;;
  private Double rebate = 0.0;;
  @Column(name = "SHIP_XTRA")
  private Double shippingExtra = 0.0;
  @Column(name = "FINAL_PRICE")
  private Double sellingPrice = 0.0;
  
  public Double getSellingPrice()
  {
    sellingPrice = productPrice + (productPrice*operatingMarginRate/100);
    sellingPrice -= rebate;
    sellingPrice += shippingExtra;
    return sellingPrice;
  }
}
