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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.uthtechnologies.mykraft.dao.entity.catalog.vendor.ProductSKUClass;
import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;
import com.uthtechnologies.mykraft.dao.entity.util.ProductPricingSupport;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_ORDER_DETAILS", indexes = {
    @Index(name = "idx_WMK_ORDER_DETAILS", columnList = "ORD_SUMM_ID, PROD_SKU_ID", unique = true)})
public class OrderDetail {

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    OrderDetail other = (OrderDetail) obj;
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
  OrderDetail()
  {
    
  }
  /**
   * To calculate the order amount
   * @param product
   * @param quantity
   */
  public OrderDetail(ProductSKUClass product, Integer quantity)
  {
    this.product = product;
    this.quantity = quantity;
    pricing = new ProductPricingSupport(this.product.getCosting().getBuyingPrice(), quantity);
  }
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "ORD_SUMM_ID", referencedColumnName = "ID")
  private OrderSummary summary;
  
  @OneToOne
  @JoinColumn(name = "PROD_SKU_ID", referencedColumnName = "ID")
  private ProductSKUClass product;
  
  @Column(name = "QTY")
  private Integer quantity = 0;
  
  @Column(name = "EST_DEL_DT", nullable = false)
  private Date estDeliveryDate;
  
  private ProductPricingSupport pricing = new ProductPricingSupport();
  @OneToMany(mappedBy = "order", orphanRemoval = true)
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
