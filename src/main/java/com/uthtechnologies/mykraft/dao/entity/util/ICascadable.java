/* ============================================================================
*
* FILE: EntityCascadable.java
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

public interface ICascadable {

  void removeAllChildren();
  void removeChild(Object child);
}
