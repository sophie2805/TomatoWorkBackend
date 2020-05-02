package com.ssophie.tomatowork.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ssophie.tomatowork.Dao.VistorsDao;

@Service
public class StatisticService {
	@Autowired
	private VistorsDao visitorDao;
	
	private Gson gson = new Gson();
	
	public String getVisitorCountsForEachDay(){
		List<String[]> result = new ArrayList<String[]>();
		List<Object[]> rawData = visitorDao.queryVisitorsForEachDay();
		for(Object[] o : rawData){
			String[] dataEachDay = new String[2];
			dataEachDay[0] = Integer.valueOf(o[0].toString()).toString();//visitor counts
			dataEachDay[1] = o[1].toString();//date
			result.add(dataEachDay);
		}
		
		return gson.toJson(result);
	}
	
	public String getHottestActionItem(){
		List<String[]> result = new ArrayList<String[]>();
		List<Object[]> rawData = visitorDao.queryHottestActions();
		for(Object[] o : rawData){
			String[] dataEachDay = new String[2];
			dataEachDay[0] = Integer.valueOf(o[0].toString()).toString();//click counts
			dataEachDay[1] = o[1].toString();//action item
			result.add(dataEachDay);
		}
		return gson.toJson(result);
	}
}
