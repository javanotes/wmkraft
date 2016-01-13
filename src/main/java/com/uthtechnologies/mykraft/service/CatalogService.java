/* ============================================================================
*
* FILE: CatalogService.java
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
package com.uthtechnologies.mykraft.service;

import java.util.List;
import java.util.Map;

import com.uthtechnologies.mykraft.dao.entity.catalog.ProductCategory;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLine;
import com.uthtechnologies.mykraft.dao.entity.catalog.vendor.Product;

public interface CatalogService {

  List<ProductCategory> findAllCategories();
  List<ProductLine> findProductLinesForCategory(ProductCategory c);
  List<Product> findVendorProductsForCategory(ProductCategory c);
  List<Product> findVendorProductsForProductLine(ProductLine p);
  Map<ProductCategory, List<Product>> findVendorProductsByTags(List<String> tags);
}
