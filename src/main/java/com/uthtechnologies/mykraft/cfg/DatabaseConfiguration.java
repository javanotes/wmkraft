/* ============================================================================
 *
 * FILE: DatabaseConfiguration.java
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

package com.uthtechnologies.mykraft.cfg;

import javax.sql.DataSource;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class DatabaseConfiguration {

  @Bean
  public CacheManager cacheManager() {
      return new ConcurrentMapCacheManager("CACHE_LIST_VALUE_META");
  }
  /**
   * Spring bean - datasource configuration for 'emt' schema
   * 
   * @return {@link HikariConfig}
   */
  @Bean
  @ConfigurationProperties(prefix = "datasource.emt")
  public HikariConfig emtHikariConfig() {
    HikariConfig config = BeanUtils.instantiate(HikariConfig.class);
    return config;
  }

  

  /**
   * Spring bean - Datasource on 'emt' schema. This is a primary bean. So any
   * autowiring by type {@link DataSource} would return this instance.
   * 
   * @return {@link HikariDataSource}
   */
  @Bean
  @Primary
  @ConfigurationProperties(prefix = "datasource.emt")
  public HikariDataSource edmtDataSource() {
    log.info("---Initializing HIKARI CP for EMT_CONNECTION_POOL_BEAN -----");
    final HikariDataSource ds = new HikariDataSource(emtHikariConfig());
    ds.addDataSourceProperty("cachePrepStmts", true);
    ds.addDataSourceProperty("prepStmtCacheSize", 250);
    ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
    ds.addDataSourceProperty("useServerPrepStmts", true);
    return ds;
  }

  /**
   * Spring bean - {@link JdbcTemplate} on 'emt' schema. This is a primary bean.
   * So any autowiring by type {@link JdbcTemplate} would return this instance.
   * 
   * @return {@link JdbcTemplate}
   */
  @Bean
  @Primary
  public JdbcTemplate jdbcTemplateCore() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(edmtDataSource());
    return jdbcTemplate;
  }

  
}
