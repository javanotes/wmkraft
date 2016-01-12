/* ============================================================================
*
* FILE: UserRole.java
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
package com.uthtechnologies.mykraft.dao.entity.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.ListValue;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_ROLE_PERMISSIONS")
public class RolePermission {

  public RolePermission()
  {
    
  }
  public RolePermission(ListValue permission, ListValue role) {
    super();
    this.permission = permission;
    this.role = role;
  }
  //Role is a set of permissions
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @OneToOne
  @JoinColumn(name = "PERM", referencedColumnName = "ID")
  private ListValue permission;
  @OneToOne
  @JoinColumn(name = "ROLE", referencedColumnName = "ID")
  private ListValue role;
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RolePermission other = (RolePermission) obj;
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
  
}
