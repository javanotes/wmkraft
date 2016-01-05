/* ============================================================================
*
* FILE: AuditSupport.java
*
* MODULE DESCRIPTION:
* See class description
*
* Copyright (C) 2015 by
* 
* All rights reserved
*
* ============================================================================
*/

package com.uthtechnologies.mykraft.dao.entity.util;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.uthtechnologies.mykraft.dao.entity.auth.User;

import lombok.Data;
/**
 * This class addresses the cross cutting concern of audit logging feature.
 * Any entity, needing to be audit log enabled, should compose this class (has-a) in itself.
 * At database level, that would add the columns to the entity table.
 * @author esutdal
 *
 */
@Embeddable
@Data
public class AuditSupport {

  public AuditSupport() {
    super();
  }

  public AuditSupport(Date lastUpdated, User lastUpdatedBy) {
    super();
    this.lastUpdated = lastUpdated;
    this.lastUpdatedBy = lastUpdatedBy;
  }

  @Column(name = "UPDATED_AT")
  protected Date lastUpdated;
  
  @OneToOne
  @JoinColumn(name = "UPDATED_BY", referencedColumnName = "LOGIN_ID")
  protected User lastUpdatedBy;
}
