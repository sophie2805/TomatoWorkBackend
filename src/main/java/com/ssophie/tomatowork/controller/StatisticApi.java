package com.ssophie.tomatowork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssophie.tomatowork.service.StatisticService;

//import com.ssophie.tomatowork.service.VisitorService;

@RestController
@RequestMapping("/api/statistic")
public class StatisticApi {
	
	@Autowired
	private StatisticService statisticService;
	
	@GetMapping(value="/visitorSummary")
	public String getVisitorCountsEachDay(){
		return statisticService.getVisitorCountsForEachDay();
	}
	
	@GetMapping(value="/popularItemSummary")
	public String getPopularItems(){
		return statisticService.getHottestActionItem();
	}
	
	@GetMapping(value="/test")
	public String test(){
		return "OK";
	}
	
	
}
