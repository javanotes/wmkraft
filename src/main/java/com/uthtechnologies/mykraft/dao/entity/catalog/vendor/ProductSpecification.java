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

import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLineSpecification;

import lombok.Data;

@Entity
@Data
@Table(name = "WMK_VENDOR_PRODUCT_SPECS", indexes = {
    @Index(name = "idx_VENDOR_PRODUCT_SPEC", columnList = "PROD_ID, SPEC_ID", unique = true)})
public class ProductSpecification {
  
  ProductSpecification() {
    super();
  }
  public ProductSpecification(ProductLineSpecification spec) {
    super();
    this.spec = spec;
  }
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "PROD_ID", referencedColumnName = "ID")
  private Product product;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SPEC_ID", referencedColumnName = "ID")
  private ProductLineSpecification spec;
  @Column(name = "SPEC_DETL", columnDefinition = "MEDIUMTEXT")
  private String descript;
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductSpecification other = (ProductSpecification) obj;
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
