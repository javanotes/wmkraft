/* ============================================================================
*
* FILE: OrderFulfillment.java
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

import javax.persistence.Column;
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
@Table(name = "WMK_ORDER_FULFILLMENT")
public class OrderFulfillment {

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @OneToOne
  @JoinColumn(name="ORD_DETL_ID", referencedColumnName = "ID")
  private OrderDetail order;
  
  @OneToOne
  @JoinColumn(name = "STAGE_ID", referencedColumnName = "ID")
  private ListValue stage;
  @Column(name = "STAGE_DT")
  private Date completionDate;
}
