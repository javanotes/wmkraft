/* ============================================================================
*
* FILE: UserRoleRepository.java
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
package com.uthtechnologies.mykraft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uthtechnologies.mykraft.dao.entity.ListValue;
import com.uthtechnologies.mykraft.dao.entity.auth.UserRole;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

  UserRole findByRole(ListValue role);
}
