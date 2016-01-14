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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;
import com.uthtechnologies.mykraft.dao.entity.util.ProductCostingSupport;
import com.uthtechnologies.mykraft.dao.entity.util.ProductDimensionSupport;

import lombok.Data;

@Entity
@Data
@Table(name = "WMK_PRODUCT_SKU_CLASS", indexes = {
    @Index(name = "idx_WMK_VENDOR_PRODUCT_sku", columnList = "SKU_TYP_ID, SKU_ATTR", unique = true)})
public class ProductSKUClass{

  public ProductSKUClass()
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
    ProductSKUClass other = (ProductSKUClass) obj;
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
  @JoinColumn(name = "SKU_TYP_ID", referencedColumnName = "ID")
  private ProductSKU skuType;
  @Column(name = "SKU_CODE", unique = true, length = 20)
  private String skuCode;
  @Column(name = "SKU_ATTR", nullable = false)
  private String skuAttrib;
  @OneToOne(mappedBy = "product")  
  private ProductStock stock;
  
  private Boolean active = true;
  
  @Column(name = "SKU_DISP_ORDER")
  private Integer skuDispOrder;
  
  private ProductDimensionSupport dimension;
  private ProductCostingSupport costing;
  
  private AuditSupport audit;
  
}
