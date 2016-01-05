/* ============================================================================
*
* FILE: OrderDetail.java
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

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.catalog.VendorProduct;
import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;
import com.uthtechnologies.mykraft.dao.entity.util.ProductPricingSupport;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_ORDER_DETAILS")
public class OrderDetail {

  OrderDetail()
  {
    
  }
  /**
   * To calculate the order amount
   * @param product
   * @param quantity
   */
  public OrderDetail(VendorProduct product, Integer quantity)
  {
    this.product = product;
    this.quantity = quantity;
    pricing = new ProductPricingSupport(this.product.getCosting().getBuyingPrice(), quantity);
  }
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @OneToOne
  @JoinColumn(name = "ORD_SUMM_ID", referencedColumnName = "ID")
  private OrderSummary summId;
  @OneToOne
  @JoinColumn(name = "PROD_ID", referencedColumnName = "ID")
  private VendorProduct product;
  @Column(name = "QTY")
  private Integer quantity = 0;
  private ProductPricingSupport pricing = new ProductPricingSupport();
  @OneToMany
  @JoinColumn(name = "ORD_DETL_ID", referencedColumnName = "ID")
  private Set<OrderFulfillment> stages = new TreeSet<>(new Comparator<OrderFulfillment>() {

    @Override
    public int compare(OrderFulfillment arg0, OrderFulfillment arg1) {
      return Integer.compare(arg0.getStage().getSortOrder(), arg1.getStage().getSortOrder());
    }
  });
  @Column(length = 400)
  private String userRemark;
  private Date remarkDt;
  private byte status = 0;//INPROC,COMPLETE,CANCEL (0,1,-1)
  private AuditSupport audit;
  private String trackingId,trackingUrl;
}
