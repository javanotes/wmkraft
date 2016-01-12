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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.ListValue;

import lombok.Data;
@Data
@Entity
@Table(name = "WMK_ORDER_FULFILLMENT", indexes = {
    @Index(name = "idx_WMK_ORDER_FULFILLMENT", columnList = "ORD_DETL_ID, STAGE_ID", unique = true)})
public class OrderFulfillment {

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name="ORD_DETL_ID", referencedColumnName = "ID")
  private OrderDetail order;
  
  @OneToOne
  @JoinColumn(name = "STAGE_ID", referencedColumnName = "ID")
  private ListValue stage;
  @Column(name = "STAGE_DT")
  private Date completionDate;
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    OrderFulfillment other = (OrderFulfillment) obj;
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
