/* ============================================================================
*
* FILE: ProductStock.java
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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_PRODUCT_STOCK")
public class ProductStock {

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PROD_ID", referencedColumnName = "ID")
  private VendorProduct product;
  
  @Column(name = "QTY_IN_STOCK")
  private Integer qtyInStock;
  @Column(name = "QTY_ORDERED")
  private Integer qtyOrdered;
  @Column(name = "EST_ARR_DT")
  private Date estArrDate;
  private AuditSupport audit;
}
