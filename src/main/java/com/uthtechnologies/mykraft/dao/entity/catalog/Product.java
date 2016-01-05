/* ============================================================================
*
* FILE: Product.java
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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;

import lombok.Data;

@Entity
@Data
@Table(name = "WMK_PRODUCT_MASTER")
public class Product implements Comparable<Product>{

  //if same product to be sold by multiple vendors
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PROD_TYP_ID")
  private Long id;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CATG_ID", referencedColumnName = "ID")
  private Category category;
  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  @JoinColumn(name = "PROD_TYP_ID", referencedColumnName = "PROD_TYP_ID")
  private Set<ProductSpecification> specs = new HashSet<>();
  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  @JoinColumn(name = "PROD_TYP_ID", referencedColumnName = "PROD_TYP_ID")  
  private Set<VendorProduct> products = new HashSet<>();
  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  @JoinColumn(name = "PROD_TYP_ID", referencedColumnName = "PROD_TYP_ID")
  private Set<ProductTag> tags = new HashSet<>();
  private AuditSupport audit;
  @Column(name = "CATG_DISP_ORDER")
  private Integer catgDispOrder;
  @Column(name = "PROD_TYP_LABEL")
  private String prodTypeLabel;
  @Override
  public int compareTo(Product o) {
    return Integer.compare(this.getCatgDispOrder(), o.getCatgDispOrder());
  }
    
}
