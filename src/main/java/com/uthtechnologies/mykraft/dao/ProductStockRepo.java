/* ============================================================================
*
* FILE: ProductStockRepo.java
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
package com.uthtechnologies.mykraft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uthtechnologies.mykraft.dao.entity.catalog.vendor.ProductStock;
@Repository
public interface ProductStockRepo extends JpaRepository<ProductStock, Long> {

}
