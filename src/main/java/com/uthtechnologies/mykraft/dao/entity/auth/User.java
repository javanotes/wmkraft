/* ============================================================================
 *
 * FILE: User.java
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

package com.uthtechnologies.mykraft.dao.entity.auth;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

import com.uthtechnologies.mykraft.dao.entity.ListValue;
import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString(exclude = { "password" })
@Entity
@Table(name = "WMK_USER_MASTER")
public class User implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "f_name", nullable = false)
  private String firstName;

  @Column(name = "l_name", nullable = false)
  private String lastName;

  @Column(name = "name")
  @Getter(AccessLevel.NONE)
  private String displayName;

  public String getDisplayName() {
    return StringUtils.isEmpty(displayName) ? userName : displayName;
  }

  @Column(name = "login_id", nullable = false, unique = true)
  private String userName;

  @Column(name = "password", nullable = false)
  private String password;
  
  @Column(name = "email")
  private String email;
  
  @Column(name = "email_alt")
  private String emailAlternate;
  
  @Column(name = "mobile")
  private String mobile;
  
  @Column(name = "mobile_alt")
  private String mobileAlternate;

  @Column(name = "last_updated_ts")
  private Date lastUpdated;
  
  @OneToOne
  @JoinColumn(name = "ROLE_ID", referencedColumnName = "USR_ROLE")
  private UserRole role;
  
  @Transient
  private String confirmPassword;
  private AuditSupport audit;
  
  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
  private Set<UserAddress> addresses = new HashSet<>();

  /**
   * 
   * @return
   */
  public UserAddress newAddress()
  {
    UserAddress addr = new UserAddress();
    addr.setUser(this);
    getAddresses().add(addr);
    return addr;
  }
  /**
   * 
   * @param type
   * @param country
   * @param city
   * @param state
   * @param addrLine1
   * @param zipCode
   * @return
   */
  public UserAddress newAddress(ListValue type, String country, String city, String state, String addrLine1, String zipCode)
  {
    UserAddress addr = new UserAddress(type, zipCode, city, addrLine1, state, country);
    addr.setUser(this);
    getAddresses().add(addr);
    return addr;
  }
}