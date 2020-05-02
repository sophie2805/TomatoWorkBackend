package com.ssophie.tomatowork.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ssophie.tomatowork.entity.Visitors;

@Repository
public interface VistorsDao extends CrudRepository<Visitors, Long> {
	@Query(value = "select count(distinct cookie_session) as visitors, DATE(create_at) as v_date from visitors group by DATE(create_at)",
			nativeQuery = true)
  List<Object[]> queryVisitorsForEachDay();

	@Query(value = "select count(distinct cookie_session) as counts, action_item from visitors group by action_item",
			nativeQuery = true)
  List<Object[]> queryHottestActions();
}
