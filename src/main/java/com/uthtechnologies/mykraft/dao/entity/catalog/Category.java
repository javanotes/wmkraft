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
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;
import com.uthtechnologies.mykraft.dao.entity.util.DescriptionSupport;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_CATEGORY_MASTER")
public class Category {

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "CODE", nullable = false, unique = true, length = 64)
  private String category;
  private DescriptionSupport descript;
  private AuditSupport audit;
  
  //this can be an expensive fetch
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "CATG_ID", referencedColumnName = "ID")
  private Set<Product> products = new HashSet<>();
}
