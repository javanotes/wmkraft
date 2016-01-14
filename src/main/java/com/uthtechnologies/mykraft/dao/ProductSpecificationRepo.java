/* ============================================================================
*
* FILE: ProductSpecificationRepo.java
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

import com.uthtechnologies.mykraft.dao.entity.catalog.vendor.ProductSpecification;

public interface ProductSpecificationRepo
    extends JpaRepository<ProductSpecification, Long> {

}