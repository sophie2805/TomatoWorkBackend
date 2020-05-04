package com.ssophie.tomatowork.controller;

import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssophie.tomatowork.entity.Visitors;
import com.ssophie.tomatowork.service.StatisticService;

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
	
	@PostMapping(value="/addVisitorEntry")
	public void addVisitorEntry(@RequestBody String actionItem, HttpServletRequest request){
		Cookie [] cookies = request.getCookies();
		String token = "";
		for (Cookie cookie : cookies) {
		     if ("tomato-token".equals(cookie.getName())) {
		          token = cookie.getValue();
		     }
		}
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
