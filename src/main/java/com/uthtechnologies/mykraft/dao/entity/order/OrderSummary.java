/* ============================================================================
*
* FILE: OrderSummary.java
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
package com.uthtechnologies.mykraft.dao.entity.order;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.auth.User;
import com.uthtechnologies.mykraft.dao.entity.auth.UserAddress;
import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_ORDER_SUMMARY")
public class OrderSummary {

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "CONFIRM_NUM")
  private String confirmationNo;
  @Column(name = "DATE_GEN")
  private Date orderDate;
  
  @ManyToOne
  @JoinColumn(name = "CUST_ID", referencedColumnName = "USER_ID")
  private User customer;
  private AuditSupport audit;
  
  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  @JoinColumn(name = "ORD_SUMM_ID", referencedColumnName = "ID")
  private Set<OrderDetail> details = new HashSet<>();
  
  @ManyToOne
  @JoinColumn(name = "SHIP_TO", referencedColumnName = "ID")
  private UserAddress shippingAddr;//TODO: all items shipping to same address
  
  @OneToOne(mappedBy = "orderId")
  private OrderPayment payment;
}
