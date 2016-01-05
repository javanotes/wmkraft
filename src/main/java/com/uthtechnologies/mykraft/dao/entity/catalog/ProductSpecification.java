/* ============================================================================
*
* FILE: ProductSpecs.java
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
@Table(name = "WMK_PRODUCT_SPECS")
public class ProductSpecification{

  public ProductSpecification()
  {
    
  }
  
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "CODE", nullable = false, length = 64)
  private String specCode;
  
  private String label;
  private Boolean searchable;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PROD_TYP_ID", referencedColumnName = "PROD_TYP_ID")
  private Product product;
  private AuditSupport audit;
  
}
