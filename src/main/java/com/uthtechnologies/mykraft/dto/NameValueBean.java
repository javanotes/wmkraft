/* ============================================================================
*
* FILE: BaseNameValueBean.java
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
package com.uthtechnologies.mykraft.dto;

import lombok.Data;

@Data
public class NameValueBean {

  public NameValueBean() {
    super();
  }
  public NameValueBean(String name, Object value) {
    super();
    this.name = name;
    this.value = value;
  }
  private String name;
  private Object value;
}
