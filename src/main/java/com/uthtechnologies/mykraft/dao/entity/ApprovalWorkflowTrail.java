/* ============================================================================
*
* FILE: ApprovalWorkflowTrail.java
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.uthtechnologies.mykraft.dao.entity.util.AuditSupport;

import lombok.Data;

@Data
@Entity
@Table(name = "WMK_APPROVAL_WORKFLOW_TRAILS")
public class ApprovalWorkflowTrail implements Comparable<ApprovalWorkflowTrail>{

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "WORKFLOW_ID")
  private ApprovalWorkflow workflow;
  @Column(columnDefinition = "MEDIUMTEXT")
  private String comments;
  private Integer seqNo;
  @NotNull
  private AuditSupport audit;
  @Override
  public int compareTo(ApprovalWorkflowTrail o) {
    return this.getSeqNo().compareTo(o.getSeqNo());
  }
}
