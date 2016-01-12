/* ============================================================================
*
* FILE: ListValueMeta.java
*
* MODULE DESCRIPTION:
* See class description
*
* Copyright (C) 2015 by
* ERICSSON
*
* The program may be used and/or copied only with the written
* permission from Ericsson Inc, or in accordance with
* the terms and conditions stipulated in the agreement/contract
* under which the program has been supplied.
*
* All rights reserved
*
* ============================================================================
*/

package com.uthtechnologies.mykraft.dao.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;

import lombok.Getter;
import lombok.Setter;
/**
 * A utility table to store key value relations.
 * Can be used for e.g to store dropdown lists, enums,
 * map entries etc. To be configured and cached
 * @author esutdal
 *
 */
@Entity
@Table(name = "WMK_LIST_VALUES", indexes = {
    @Index(name = "idx_list_val", columnList = "TYPE, NAME", unique = true)})

public class ListValue implements Serializable{/**
   * 
   */
  private static final long serialVersionUID = 8578423253330490764L;

  @Override
public int hashCode() {
  final int prime = 31;
  int result = 1;
  result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());
  return result;
}
@Override
public boolean equals(Object obj) {
  if (this == obj)
    return true;
  if (obj == null)
    return false;
  if (getClass() != obj.getClass())
    return false;
  ListValue other = (ListValue) obj;
  if (id == null) {
    //if (other.id != null)
      return false;
  } else if (!id.equals(other.id))
    return false;
  return true;
}
  @Getter@Setter
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  /**
   * An identifier for the type of relation
   */
  @Column(nullable = false)
  @Getter@Setter
  private String type;
  /**
   * An identifier for a named type
   */
  @Column(nullable = false)
  @Getter@Setter
  private String name;
  /**
   * Value of the named type
   */
  @Getter@Setter
  private String value;
  /**
   * Any extra value - optional
   */
  @Column(nullable = true)
  @Getter@Setter
  private String extra;
  /**
   * Sorting order for the named type - optional
   */
  @Column(nullable = true)
  @Getter@Setter
  private short sortOrder;
  /**
   * If the ListValueMeta is to be excluded
   */
  @Getter@Setter
  private boolean excluded = false;
  @Getter@Setter
  private AuditSupport auditLog;
  @ManyToOne
  @Getter@Setter
  private ListValue parent;
  @OneToMany(mappedBy="parent", fetch = FetchType.EAGER)
  @Getter@Setter
  private Set<ListValue> childs = new HashSet<>();
  /**
   * Creates a new child meta of this meta
   * @return
   */
  public ListValue newChild()
  {
    ListValue meta = new ListValue();
    meta.setParent(this);
    this.getChilds().add(meta);
    return meta;
  }
  
}
