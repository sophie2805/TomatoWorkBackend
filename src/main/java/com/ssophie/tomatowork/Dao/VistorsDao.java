package com.ssophie.tomatowork.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ssophie.tomatowork.entity.Visitors;

@Repository
public interface VistorsDao extends PagingAndSortingRepository<Visitors, Long>{
	@Query(value = "select count(distinct cookie_session) as cnt, DATE(create_at) as item from visitor_info group by DATE(create_at)",
			nativeQuery = true)
  List<Object[]> queryVisitorsForEachDay();

	@Query(value = "select count(cookie_session) as cnt, action_item as item from visitor_info where action_item != 'Load' group by action_item",
			nativeQuery = true)
  List<Object[]> queryHottestActions();
  
	@Query(value = "select count(distinct cookie_session) as v_total from visitor_info",
			nativeQuery = true)
  List<Object[]> queryVisitorsTotalCount();
}
