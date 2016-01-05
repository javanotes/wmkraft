/* ============================================================================
*
* FILE: ProductCrossSale.java
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_PRODUCT_UP_SELL")
public class ProductUpSell {

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "PROMO_MSG", columnDefinition = "TEXT")
  private String promotionMessage;
  @Column(name = "SEQ")
  private int sequence;
  @OneToOne
  @JoinColumn(name = "CATG_ID", referencedColumnName = "ID")
  private Category category;
  @OneToOne
  @JoinColumn(name = "PROD_ID", referencedColumnName = "ID")
  private VendorProduct product;
  @OneToOne
  @JoinColumn(name = "XREF_PROD_ID", referencedColumnName = "ID")
  private VendorProduct relatedProduct;
}