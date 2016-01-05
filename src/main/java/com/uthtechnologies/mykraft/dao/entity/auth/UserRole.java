/* ============================================================================
*
* FILE: UserTypeRole.java
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

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.ListValue;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_USER_ROLE_MASTER")
public class UserRole implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  //Is one possible combination of permissions.
  //A user(type) can have more than one roles (so that many combinations)
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  public UserRole()
  {
    
  }
  public UserRole(ListValue role)
  {
    this.role = role;
  }
  @OneToOne
  @JoinColumn(name = "USR_ROLE", referencedColumnName = "ID")
  private ListValue role;
  /**
   * Creates a new permission association for this role
   * @return
   */
  public RolePermission newRolePermission()
  {
    RolePermission perm = new RolePermission();
    perm.setRole(getRole());
    getPermissions().add(perm);
    return perm;
  }
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "ROLE", referencedColumnName = "USR_ROLE")
  private Set<RolePermission> permissions = new HashSet<>();
}
