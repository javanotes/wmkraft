/* ============================================================================
*
* FILE: FlatDynaBean.java
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

import java.util.HashMap;
import java.util.Map;

public class FlatDynaBean {

  private final Map<String, NameValueBean> nameValueMap;
  public FlatDynaBean(Map<String, NameValueBean> nameValueMap)
  {
    this.nameValueMap = nameValueMap;
  }
  public FlatDynaBean()
  {
    this(new HashMap<String, NameValueBean>());
  }
  public void addNameValue(String name, Object value)
  {
    nameValueMap.put(name, new NameValueBean(name, value));
  }
}
