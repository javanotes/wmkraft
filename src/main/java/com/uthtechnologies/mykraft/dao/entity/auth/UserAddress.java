/* ============================================================================
*
* FILE: UserAddress.java
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.ListValue;
import com.uthtechnologies.mykraft.dao.entity.util.AddressSupport;
import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "WMK_USER_ADDR_MASTER")
public class UserAddress {

  public UserAddress()
  {
    
  }
  public UserAddress(ListValue type, String zipCode, String city, String addrLine1, String state, String country)
  {
    this.address = new AddressSupport(type, addrLine1, zipCode, state, city, country);
  }
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  @Getter@Setter
  private Long id; 
  @ManyToOne
  @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
  @Getter@Setter
  private User user;
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    UserAddress other = (UserAddress) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  @Getter@Setter
  private AddressSupport address;
  @Getter@Setter
  private AuditSupport audit;
}
