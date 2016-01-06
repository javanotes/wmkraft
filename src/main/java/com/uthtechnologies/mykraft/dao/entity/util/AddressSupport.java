/* ============================================================================
*
* FILE: AddressSupport.java
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
package com.uthtechnologies.mykraft.dao.entity.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.uthtechnologies.mykraft.dao.entity.ListValue;

import lombok.Data;

@Embeddable
@Data
public class AddressSupport {

  public AddressSupport()
  {
    
  }
  public AddressSupport(ListValue type, String addrLine1, String addrLine2,
      String addrLine3, String landMark, String zipCode, String state,
      String city, String country) {
    super();
    this.type = type;
    this.addrLine1 = addrLine1;
    this.addrLine2 = addrLine2;
    this.addrLine3 = addrLine3;
    this.landMark = landMark;
    this.zipCode = zipCode;
    this.state = state;
    this.city = city;
    this.country = country;
  }
  public AddressSupport(ListValue type, String addrLine1, String zipCode, String state,
      String city, String country) {
    super();
    this.type = type;
    this.addrLine1 = addrLine1;
    this.zipCode = zipCode;
    this.state = state;
    this.city = city;
    this.country = country;
  }
  @OneToOne
  @JoinColumn(name = "ADDR_TYP", referencedColumnName = "ID")
  private ListValue type;
  
  @Column(name = "ADDR_1")
  private String addrLine1;
  @Column(name = "ADDR_2")
  private String addrLine2;
  @Column(name = "ADDR_3")
  private String addrLine3;
  @Column(name = "LANDMARK")
  private String landMark;
  @Column(name = "ZIP")
  private String zipCode;
  private String state, city;
  private String country;
  
}
