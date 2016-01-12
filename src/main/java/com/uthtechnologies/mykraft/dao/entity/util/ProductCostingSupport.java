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
public class ProductCostingSupport {

  public ProductCostingSupport() {
    super();
  }

  public ProductCostingSupport(Double taxRate, Double costPrice,
      Double costOfPkg, Double costOfDelivery) {
    super();
    this.taxRate = taxRate;
    this.costPrice = costPrice;
    this.costOfPkg = costOfPkg;
    this.costOfDelivery = costOfDelivery;
  }

  @Column(name = "SUPP_TAX_RATE")  
  private Double taxRate = 0.0;;
  @Column(name = "SUPP_SELL_COST")
  private Double costPrice = 0.0;;
  @Column(name = "SUPP_PKG_COST")
  private Double costOfPkg = 0.0;;
  @Column(name = "SUPP_SHIP_COST")
  private Double costOfDelivery = 0.0;;
  @Column(name = "FINAL_COST")
  private Double buyingPrice = 0.0;
  
  public Double getBuyingPrice()
  {
    buyingPrice = costPrice + (costPrice*taxRate/100);
    buyingPrice += costOfPkg + costOfDelivery;
    return buyingPrice;
  }
}
