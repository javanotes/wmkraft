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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.catalog.vendor.Product;
import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;

import lombok.Data;

@Entity
@Data
@Table(name = "WMK_PRODUCT_LINES", indexes = {
    @Index(name = "idx_WMK_ORDER_DETAILS", columnList = "CATG_ID, PROD_TYP", unique = true)})
public class ProductLine implements Comparable<ProductLine>{

  //if same product to be sold by multiple vendors
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PROD_TYP_ID")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "CATG_ID", referencedColumnName = "ID")
  private ProductCategory category;
  
  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "product", orphanRemoval = true)
  private Set<ProductLineSpecification> specs = new HashSet<>();
  
  public ProductLineSpecification newProductSpecification()
  {
    ProductLineSpecification ps = new ProductLineSpecification();
    ps.setProduct(this);
    getSpecs().add(ps);
    return ps;
  }
  
  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "productLine", orphanRemoval = true)
  private Set<Product> products = new HashSet<>();
  public Product newVendorProduct()
  {
    Product vp = new Product();
    vp.setProductLine(this);
    getProducts().add(vp);
    return vp;
  }
  
  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "product", orphanRemoval = true)
  private Set<ProductLineTag> tags = new HashSet<>();
  public ProductLineTag newProductTag()
  {
    ProductLineTag pt = new ProductLineTag();
    pt.setProduct(this);
    getTags().add(pt);
    return pt;
  }
  
  private AuditSupport audit;
  @Column(name = "CATG_DISP_ORDER")
  private Integer catgDispOrder;
  @Column(name = "PROD_TYP", nullable = false)
  private String prodTypeLabel;
  @Override
  public int compareTo(ProductLine o) {
    return Integer.compare(this.getCatgDispOrder(), o.getCatgDispOrder());
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductLine other = (ProductLine) obj;
    if (id == null) {
      //if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());
    return result;
  }
    
}
