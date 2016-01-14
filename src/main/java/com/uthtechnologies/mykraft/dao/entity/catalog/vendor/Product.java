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

import java.nio.charset.StandardCharsets;
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

import org.apache.commons.lang3.StringUtils;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.uthtechnologies.mykraft.dao.entity.auth.User;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLine;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLineSpecification;
import com.uthtechnologies.mykraft.dao.entity.catalog.xref.ProductCrossSell;
import com.uthtechnologies.mykraft.dao.entity.catalog.xref.ProductFeatured;
import com.uthtechnologies.mykraft.dao.entity.catalog.xref.ProductUpSell;
import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;
import com.uthtechnologies.mykraft.dao.entity.util.DescriptionSupport;

import lombok.Data;

@Entity
@Data
@Table(name = "WMK_PRODUCT_MASTER", indexes = {
    @Index(name = "idx_WMK_VENDOR_PRODUCT", columnList = "PROD_TYP_ID, VEND_ID, PROD_LABEL", unique = true)})
public class Product implements Comparable<Product>{

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Product other = (Product) obj;
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
  @JoinColumn(name = "PROD_TYP_ID", referencedColumnName = "PROD_TYP_ID")
  private ProductLine productLine;
  
  @Column(name = "PROD_CODE", unique = true, length = 32)
  private String vendorProdCode;
  
  @Column(name = "PROD_LABEL", nullable = false)
  private String vendorProdLabel;
  
  @ManyToOne
  @JoinColumn(name = "VEND_ID", referencedColumnName = "USER_ID")
  private User vendor;
  private Boolean active = true;
  
  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "product", orphanRemoval = true)
  private Set<ProductSpecification> specifications = new HashSet<>();
  
  @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "product", orphanRemoval = true)  
  private Set<ProductSKU> sku = new HashSet<>();
  /**
   * New product sku type
   * @return
   */
  public ProductSKU newSKU(String code, String attrib)
  {
    ProductSKU sku = new ProductSKU();
    sku.setProduct(this);
    sku.setSkuCode(code);
    sku.setSkuAttrib(attrib);
    
    HashCode h = Hashing.md5().newHasher()
    .putString(getVendorProdCode(), StandardCharsets.UTF_8)
    .putString(sku.getSkuCode(), StandardCharsets.UTF_8)
    .putString(sku.getSkuAttrib(), StandardCharsets.UTF_8).hash();
    
    StringBuilder s = new StringBuilder()
        .append(StringUtils.rightPad(StringUtils.left(sku.getSkuCode(), 3).toUpperCase(), 3, 'X'))
        .append(StringUtils.rightPad(StringUtils.left(sku.getSkuAttrib(), 2).toUpperCase(), 2, 'X'))
        .append(StringUtils.leftPad(StringUtils.right(String.valueOf(Math.abs(h.asLong())), 12), 12, '0'));
    
    sku.setSkuID(s.toString());
    getSku().add(sku);
    return sku;
  }
  
  @Column(name = "PROD_DISP_ORDER")
  private Integer prodDispOrder;
  /**
   * New specification based on product type
   * @return
   */
  public ProductSpecification newSpecification(ProductLineSpecification prodLineSpec)
  {
    ProductSpecification spec = new ProductSpecification(prodLineSpec);
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
  
  private DescriptionSupport descript;
  
  private AuditSupport audit;
  @Override
  public int compareTo(Product o) {
    int prodSpecCompared = this.getProductLine().compareTo(o.getProductLine());
    return prodSpecCompared == 0 ? 
        Integer.compare(this.getProdDispOrder(), o.getProdDispOrder())
        : prodSpecCompared;
  }
}
