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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_PRODUCTLINE_SPECS", indexes = {
    @Index(name = "idx_VENDOR_PRODUCT_SPEC", columnList = "PROD_TYP_ID, CODE", unique = true)})
public class ProductLineSpecification{

  public ProductLineSpecification()
  {
    
  }
  
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "CODE", nullable = false, length = 64)
  private String specCode;
  
  private String label;
  private Boolean searchable;
  @ManyToOne
  @JoinColumn(name = "PROD_TYP_ID", referencedColumnName = "PROD_TYP_ID")
  private ProductLine product;
  private AuditSupport audit;
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductLineSpecification other = (ProductLineSpecification) obj;
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
