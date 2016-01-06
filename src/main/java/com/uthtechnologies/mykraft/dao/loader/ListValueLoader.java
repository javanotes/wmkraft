/* ============================================================================
 *
 * FILE: UserLoader.java
 *
 * MODULE DESCRIPTION:
 * See class description
 *
 * Copyright (C) 2015 by
 * ERICSSON
 *
 * The program may be used and/or copied only with the written
 * permission from Ericsson Inc, or in accordance with
 * the terms and conditions stipulated in the agreement/contract
 * under which the program has been supplied.
 *
 * All rights reserved
 *
 * ============================================================================
 */

package com.uthtechnologies.mykraft.dao.loader;

/**
 * Startup script for system users data setup.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.uthtechnologies.mykraft.dao.ListValueRepo;
import com.uthtechnologies.mykraft.dao.UserRepo;
import com.uthtechnologies.mykraft.dao.UserRoleRepo;
import com.uthtechnologies.mykraft.dao.entity.ListValue;
import com.uthtechnologies.mykraft.dao.entity.auth.User;
import com.uthtechnologies.mykraft.dao.entity.auth.UserAddress;
import com.uthtechnologies.mykraft.dao.entity.auth.UserRole;

@Component
@Order(0)
public class ListValueLoader implements CommandLineRunner {

  @Autowired
  private ListValueRepo lvRepo;
 
  @Transactional
  @Override
  public void run(String... args) throws Exception {
    
    ListValue lv;
    //Roles
    
    lv = lvRepo.findByTypeAndName("ROLE", "ADMIN");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ROLE");
      lv.setName("ADMIN");
      lv = lvRepo.save(lv);
            
    }
    
    lv = lvRepo.findByTypeAndName("ROLE", "MGMT");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ROLE");
      lv.setName("MGMT");
      lv = lvRepo.save(lv);
            
    }
    
    
    lv = lvRepo.findByTypeAndName("ROLE", "VENDOR");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ROLE");
      lv.setName("VENDOR");
      lv = lvRepo.save(lv);
            
    }
    
    lv = lvRepo.findByTypeAndName("ROLE", "CUSTOMER");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ROLE");
      lv.setName("CUSTOMER");
      lv = lvRepo.save(lv);
            
    }
    
    //Permissions, probably based on actions allowed
    //so these can be mapped to urls/pages
    lv = lvRepo.findByTypeAndName("PERMISSION", "READ");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("PERMISSION");
      lv.setName("READ");
      lv = lvRepo.save(lv);
            
    }
    
    lv = lvRepo.findByTypeAndName("PERMISSION", "WRITE");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("PERMISSION");
      lv.setName("WRITE");
      lv = lvRepo.save(lv);
            
    }
    
    lv = lvRepo.findByTypeAndName("PERMISSION", "EXEC");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("PERMISSION");
      lv.setName("EXEC");
      lv = lvRepo.save(lv);
            
    }
    
    lv = lvRepo.findByTypeAndName("PERMISSION", "DELETE");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("PERMISSION");
      lv.setName("DELETE");
      lv = lvRepo.save(lv);
            
    }
    
    lv = lvRepo.findByTypeAndName("ORDERFULFILLSTAGE", "READY_TO_PACK");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ORDERFULFILLSTAGE");
      lv.setName("READY_TO_PACK");
      lv.setSortOrder((short) 1);
      lv = lvRepo.save(lv);
            
    }
    
    lv = lvRepo.findByTypeAndName("ORDERFULFILLSTAGE", "PACK_COMPLETE");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ORDERFULFILLSTAGE");
      lv.setName("PACK_COMPLETE");
      lv.setSortOrder((short) 2);
      lv = lvRepo.save(lv);
            
    }
    lv = lvRepo.findByTypeAndName("ORDERFULFILLSTAGE", "GENERATE_INVOICE");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ORDERFULFILLSTAGE");
      lv.setName("GENERATE_INVOICE");
      lv.setSortOrder((short) 3);
      lv = lvRepo.save(lv);
            
    }
    lv = lvRepo.findByTypeAndName("ORDERFULFILLSTAGE", "READY_TO_SHIP");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ORDERFULFILLSTAGE");
      lv.setName("READY_TO_SHIP");
      lv.setSortOrder((short) 4);
      lv = lvRepo.save(lv);
            
    }
    lv = lvRepo.findByTypeAndName("ORDERFULFILLSTAGE", "OUT_FOR_DELIVERY");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ORDERFULFILLSTAGE");
      lv.setName("OUT_FOR_DELIVERY");
      lv.setSortOrder((short) 5);
      lv = lvRepo.save(lv);
            
    }
    lv = lvRepo.findByTypeAndName("ORDERFULFILLSTAGE", "DELIVERY_COMPLETE");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ORDERFULFILLSTAGE");
      lv.setName("DELIVERY_COMPLETE");
      lv.setSortOrder((short) 6);
      lv = lvRepo.save(lv);
            
    }
    lv = lvRepo.findByTypeAndName("ORDERFULFILLSTAGE", "DELIVERY_FAIL");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ORDERFULFILLSTAGE");
      lv.setName("DELIVERY_FAIL");
      lv.setSortOrder((short) 6);
      lv = lvRepo.save(lv);
            
    }
    
    lv = lvRepo.findByTypeAndName("ADDRTYPE", "HOME");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ADDRTYPE");
      lv.setName("HOME");
      lv = lvRepo.save(lv);
            
    }
    lv = lvRepo.findByTypeAndName("ADDRTYPE", "WORK");
    if(lv == null)
    {
      lv = new ListValue();
      lv.setType("ADDRTYPE");
      lv.setName("WORK");
      lv = lvRepo.save(lv);
            
    }
    
    createUserRoles();
    createAdminUsers();
           
  }

  @Autowired
  private UserRepo userRepo;
  private void createAdminUsers() {
    User user = userRepo.findByUserName("admin");
    if(user == null)
    {
      UserRole role = userRoleRepo.findByRole(lvRepo.findByTypeAndName("ROLE", "SYSADMIN"));
      
      BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
      user = new User();
      user.setFirstName("Admin");
      user.setLastName("User");
      user.setDisplayName("System Admin");
      user.setUserName("admin");
      user.setPassword(enc.encode("admin"));
      user.setEmail("admin@wmkraft.com");
      user.setEmailAlternate("alt_admin@wmkraft.com");
      user.setMobile("+91 9831001393");
      user.setMobileAlternate("+91 9903579234");
      user.setRole(role);
      user = userRepo.save(user);
      
      UserAddress a = user.newAddress(lvRepo.findByTypeAndName("ADDRTYPE", "HOME"), "India", "Kolkata", "West Bengal", "9/1 Khelat Babu Lane", "700037");
      a.getAddress().setLandMark("Near Tala P.S");
      userRepo.save(user);
    }
    else
    {
      if(user.getAddresses().isEmpty())
      {
        UserAddress a = user.newAddress(lvRepo.findByTypeAndName("ADDRTYPE", "HOME"), "India", "Kolkata", "West Bengal", "9/1 Khelat Babu Lane", "700037");
        a.getAddress().setLandMark("Near Tala P.S");
        userRepo.save(user);
      }
    }
    
    
  }

  @Autowired
  private UserRoleRepo userRoleRepo;
  //create different user types by assigning roles to them
  private void createUserRoles() {
    ListValue lv = lvRepo.findByTypeAndName("ROLE", "SYSADMIN");
    UserRole role = userRoleRepo.findByRole(lv);
    if(role == null)
    {
      role = new UserRole(lv);
      role.newRolePermission().setPermission(lvRepo.findByTypeAndName("PERMISSION", "READ"));
      role.newRolePermission().setPermission(lvRepo.findByTypeAndName("PERMISSION", "WRITE"));
      role.newRolePermission().setPermission(lvRepo.findByTypeAndName("PERMISSION", "EXEC"));
      role.newRolePermission().setPermission(lvRepo.findByTypeAndName("PERMISSION", "DELETE"));
      
      userRoleRepo.save(role);
    }
    
    lv = lvRepo.findByTypeAndName("ROLE", "MGMT");
    role = userRoleRepo.findByRole(lv);
    if(role == null)
    {
      role = new UserRole(lv);
      role.newRolePermission().setPermission(lvRepo.findByTypeAndName("PERMISSION", "READ"));
      role.newRolePermission().setPermission(lvRepo.findByTypeAndName("PERMISSION", "WRITE"));
      role.newRolePermission().setPermission(lvRepo.findByTypeAndName("PERMISSION", "DELETE"));
      
      userRoleRepo.save(role);
    }
    
  }

  
}
