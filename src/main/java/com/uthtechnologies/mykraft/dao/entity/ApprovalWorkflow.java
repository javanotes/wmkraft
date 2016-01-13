/* ============================================================================
*
* FILE: ApprovalWorkflow.java
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
package com.uthtechnologies.mykraft.dao.entity;

import java.util.Set;
import java.util.TreeSet;

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

import com.uthtechnologies.mykraft.dao.entity.auth.User;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_APPROVAL_WORKFLOW")
public class ApprovalWorkflow {

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(columnDefinition = "LONGTEXT")
  private String itemPayload;//json/xml
  @OneToOne
  @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
  private ListValue status;
  @OneToOne
  @JoinColumn(name = "REQ_TYP_ID", referencedColumnName = "ID")
  private ListValue requestType;
  @OneToOne
  @JoinColumn(name = "REQ_BY", referencedColumnName = "LOGIN_ID")
  private User requestedBy;
  
  @OneToMany(mappedBy = "workflow", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<ApprovalWorkflowTrail> trails = new TreeSet<>();
}
