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

import com.uthtechnologies.mykraft.dao.entity.catalog.Category;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLine;
import com.uthtechnologies.mykraft.dao.entity.catalog.VendorProduct;

public interface CatalogService {

  List<Category> findAllCategories();
  List<ProductLine> findProductLinesForCategory(Category c);
  List<VendorProduct> findVendorProductsForCategory(Category c);
  List<VendorProduct> findVendorProductsForProductLine(ProductLine p);
  Map<Category, List<VendorProduct>> findVendorProductsByTags(List<String> tags);
}
