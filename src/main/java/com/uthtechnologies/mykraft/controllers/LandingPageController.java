/* ============================================================================
*
* FILE: LandingPageController.java
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
package com.uthtechnologies.mykraft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LandingPageController {
  @RequestMapping(value = { "/" })
  public String goToHome() {
    String view = "eshopper/index";
    log.info("View - {} \t For user - {}", view, null);
    return view;
  }
}
