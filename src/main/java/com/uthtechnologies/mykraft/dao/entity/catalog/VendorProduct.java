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
package com.uthtechnologies.mykraft.dao.entity.catalog;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.auth.User;
import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;
import com.uthtechnologies.mykraft.dao.entity.util.DescriptionSupport;
import com.uthtechnologies.mykraft.dao.entity.util.ProductCostingSupport;
import com.uthtechnologies.mykraft.dao.entity.util.ProductDimensionSupport;

import lombok.Data;

@Entity
@Data
@Table(name = "WMK_VENDOR_PRODUCT")
public class VendorProduct implements Comparable<VendorProduct>{

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PROD_TYP_ID", referencedColumnName = "PROD_TYP_ID")
  private ProductLine productLine;
  @ManyToOne
  @JoinColumn(name = "VEND_ID", referencedColumnName = "USER_ID")
  private User vendor;
  private Boolean active = true;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "PROD_ID", referencedColumnName = "ID")
  private Set<VendorProductSpecification> specifications = new HashSet<>();
  
  @Column(name = "PROD_DISP_ORDER")
  private Integer prodDispOrder;
  /**
   * New specification based on product type
   * @return
   */
  public VendorProductSpecification newSpecification(ProductLineSpecification prodLineSpec)
  {
    VendorProductSpecification spec = new VendorProductSpecification(prodLineSpec);
    spec.setProduct(this);
    prodLineSpec.setProduct(getProductLine());
    
    specifications.add(spec);
    return spec;
  }
  /**
   * Creates a new associated product. Category should be different ??
   * @return
   */
  public ProductCrossSell newCrosSellProduct()
  {
    ProductCrossSell cs = new ProductCrossSell();
    //cs.setCategory(getProduct().getCategory());
    cs.setProduct(this);
    return cs;
  }
  /**
   * Creates a new associated product. Category should be same or different ??
   * @return
   */
  public ProductUpSell newUpSellProduct()
  {
    ProductUpSell cs = new ProductUpSell();
    cs.setCategory(getProductLine().getCategory());
    cs.setProduct(this);
    return cs;
  }
  /**
   * Creates a new associated product. Category may be same or different ??
   * @return
   */
  public ProductFeatured newFeaturedProduct()
  {
    ProductFeatured cs = new ProductFeatured();
    cs.setCategory(getProductLine().getCategory());
    cs.setProduct(this);
    return cs;
  }
  private ProductDimensionSupport dimension;
  private ProductCostingSupport costing;
  private DescriptionSupport descript;
  
  private AuditSupport audit;
  @Override
  public int compareTo(VendorProduct o) {
    int prodSpecCompared = this.getProductLine().compareTo(o.getProductLine());
    return prodSpecCompared == 0 ? 
        Integer.compare(this.getProdDispOrder(), o.getProdDispOrder())
        : prodSpecCompared;
  }
}
