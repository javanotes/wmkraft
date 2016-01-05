/* ============================================================================
*
* FILE: OrderPayment.java
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

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_ORDER_PAYMENTS")
public class OrderPayment {

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @OneToOne
  @JoinColumn(name = "ORD_SUMM_ID", referencedColumnName = "ID")
  private OrderSummary orderId;
  @Column(name = "PMT_METH")
  private String pmtMethod;//CASH,CCARD,DCARD,CHEQ,NEFT
  @Column(name = "PMT_AMT")
  private Double pmtAmount;
  private String txnRef;
  private String cardNo, cardTyp;
  private String currency = "INR";
  private Timestamp txnTime;
}
