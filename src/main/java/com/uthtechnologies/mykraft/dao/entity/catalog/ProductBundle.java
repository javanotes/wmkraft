/* ============================================================================
*
* FILE: ProductBundle.java
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

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.uthtechnologies.mykraft.dao.entity.util.ProductBundlePK;

import lombok.Data;

@Entity
@Data
@Table(name = "WMK_PRODUCT_BUNDLE")
public class ProductBundle {
  @EmbeddedId
  @GeneratedValue(generator = "custom-seq")
  @GenericGenerator(name = "custom-seq", strategy = "com.uthtechnologies.mykraft.dao.entity.util.SelectiveSequenceIdGenerator")
  private ProductBundlePK bundleId;
}
