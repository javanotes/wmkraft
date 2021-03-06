/* ============================================================================
*
* FILE: VendorProduct.java
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
package com.uthtechnologies.mykraft.dao.entity.catalog.vendor;

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

import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;

import lombok.Data;

@Entity
@Data
@Table(name = "WMK_PRODUCT_SKU", indexes = {
    @Index(name = "idx_WMK_VENDOR_PRODUCT_sku", columnList = "PROD_ID, SKU_TYPE", unique = true)})
public class ProductSKU{

  public ProductSKU()
  {
    
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductSKU other = (ProductSKU) obj;
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
  
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PROD_ID", referencedColumnName = "ID")
  private Product product;
  
  @Column(name = "SKU_TYPE", nullable = false)
  private String skuType;
  
  @OneToMany(mappedBy = "skuType", cascade = {CascadeType.ALL})
  private Set<ProductSKUClass> attribs = new HashSet<>();
  
  public ProductSKUClass newSKUClass(String attrib)
  {
    ProductSKUClass c = new ProductSKUClass();
    c.setSkuType(this);
    c.setSkuAttrib(attrib);
    getAttribs().add(c);
    return c;
  }
  
  private AuditSupport audit;
  
}
