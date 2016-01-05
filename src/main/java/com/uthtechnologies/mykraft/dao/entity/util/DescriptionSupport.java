/* ============================================================================
*
* FILE: DescriptionSupport.java
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

import lombok.Data;

@Embeddable
@Data
public class DescriptionSupport {

  @Column(name = "DESC_SHORT", length = 255)
  private String descShort;
  @Column(name = "DESC_LONG", length = 4000)
  private String descLong;
}
