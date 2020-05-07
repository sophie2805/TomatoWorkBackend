package com.ssophie.tomatowork.controller;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssophie.tomatowork.entity.StatisticEntity;
import com.ssophie.tomatowork.service.StatisticService;

@RestController
@RequestMapping("/api/statistic")
public class StatisticApi {
	
	@Autowired
	private StatisticService statisticService;
	
	@GetMapping(value="/visitorDailyCounts")
	public Page<StatisticEntity> getVisitorCountsEachDay(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "4") int pageSize){
		Pageable pageable = PageRequest.of(pageNum-1, pageSize);
		List<StatisticEntity> result = statisticService.getVisitorsForEachDay();
		int start = (int)pageable.getOffset();
	    int end = (start + pageable.getPageSize()) > result.size() ? result.size() : (start + pageable.getPageSize());
		Page<StatisticEntity> page = new PageImpl<>(result.subList(start, end), pageable, result.size());
		return page;
	}
	
	@GetMapping(value="/visitorTotalCount")
	public Integer getVisitorTotalCount(){
		return statisticService.getVisitorsTotalCount();
	}
	
	@GetMapping(value="/popularItemSummary")
	public List<StatisticEntity> getPopularItems(){
		return statisticService.getHottestActionItem();
	}
	
	@PostMapping(value="/addVisitorEntry")
	public void addVisitorEntry(@RequestParam("actionItem") String actionItem, HttpServletRequest request){
		Cookie [] cookies = request.getCookies();
		String token = "";
		for (Cookie cookie : cookies) {
		     if ("tomato-token".equals(cookie.getName())) {
		          token = cookie.getValue();
		     }
		}
		System.out.println("---------" + actionItem);
		statisticService.addVisitorEntry(actionItem, getClientIpAddress(request), token);
	}
	
	@PostMapping(value="/load")
	public void setCookieWhenFirstLoad(HttpServletRequest request, HttpServletResponse response){
		String token = generateToken();
		response.addCookie(new Cookie("tomato-token", token));
		statisticService.addVisitorEntry("Load", getClientIpAddress(request), token);
	}
	
	private String getClientIpAddress(HttpServletRequest request) {
	    String xForwardedForHeader = request.getHeader("X-Forwarded-For");
	    if (xForwardedForHeader == null) {
	        return request.getRemoteAddr();
	    } else {
	        return new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
	    }
	}
	
	private String generateToken(){
		int r = (int) (Math.random() * 1000);
		long now = new Date().getTime();
		return ""+now+"_"+r;
	}
	
}
