/* ============================================================================
*
* FILE: ListValueMetaRepository.java
*
* MODULE DESCRIPTION:
* See class description
*
*
*
* ============================================================================
*/

package com.uthtechnologies.mykraft.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uthtechnologies.mykraft.dao.entity.ListValue;
@Repository
public interface ListValueRepo
    extends JpaRepository<ListValue, Long> {

  @Cacheable("CACHE_LIST_VALUE_META")
  List<ListValue> findByType(String type);
  @Cacheable("CACHE_LIST_VALUE_META")
  ListValue findByTypeAndName(String type, String name);
  @Cacheable("CACHE_LIST_VALUE_META")
  List<ListValue> findByName(String name);
}
