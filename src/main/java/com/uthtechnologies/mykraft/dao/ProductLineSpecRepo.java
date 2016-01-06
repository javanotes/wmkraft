/* ============================================================================
*
* FILE: ProductLineSpecRepo.java
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

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLineSpecification;

public interface ProductLineSpecRepo
    extends JpaRepository<ProductLineSpecification, Long> {

  @Query("select u from ProductLineSpecification u where u.product.id = ?1")
  public List<ProductLineSpecification> findSpecificationsForProductLine(Long prodLineId);
}
