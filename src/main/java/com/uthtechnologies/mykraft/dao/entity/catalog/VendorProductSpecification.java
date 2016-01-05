/* ============================================================================
*
* FILE: VendorProductSpecs.java
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

import lombok.Data;

@Entity
@Data
@Table(name = "WMK_VENDOR_PRODUCT_SPECS")
public class VendorProductSpecification {

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PROD_ID", referencedColumnName = "ID")
  private VendorProduct product;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SPEC_ID", referencedColumnName = "ID")
  private ProductSpecification spec;
  @Column(name = "SPEC_DETL", columnDefinition = "MEDIUMTEXT")
  private String descript;
  
}
