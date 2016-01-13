/* ============================================================================
*
* FILE: Category.java
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
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;
import com.uthtechnologies.mykraft.dao.entity.util.ICascadable;
import com.uthtechnologies.mykraft.dao.entity.util.DescriptionSupport;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_PRODUCT_CATEGORY")
public class ProductCategory implements ICascadable{

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "CODE", nullable = false, unique = true, length = 64)
  private String category;
  private DescriptionSupport descript;
  private AuditSupport audit;
  
  //this can be an expensive fetch
  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "category", orphanRemoval = true)
  private Set<ProductLine> products = new HashSet<>();
  
  public ProductLine newProductLine()
  {
    ProductLine pl = new ProductLine();
    pl.setCategory(this);
    getProducts().add(pl);
    return pl;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductCategory other = (ProductCategory) obj;
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

  @Override
  public void removeAllChildren() {
    for(Iterator<ProductLine> iter = products.iterator(); iter.hasNext();)
    {
      ProductLine pl = iter.next();
      pl.setCategory(null);
      iter.remove();
    }
    
  }

  @Override
  public void removeChild(Object child) {
    if(products.remove(child))
    {
      ((ProductLine)child).setCategory(null);
    }
    
  }
}
