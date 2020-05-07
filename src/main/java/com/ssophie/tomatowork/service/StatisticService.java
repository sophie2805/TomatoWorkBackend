package com.ssophie.tomatowork.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssophie.tomatowork.Dao.VistorsDao;
import com.ssophie.tomatowork.entity.StatisticEntity;
import com.ssophie.tomatowork.entity.Visitors;

@Service
public class StatisticService {
	
	@Autowired
	private VistorsDao visitorDao;
	
		public List<StatisticEntity> getVisitorsForEachDay(){
			List<StatisticEntity> result = new ArrayList<>();
			List<Object[]> rawData = visitorDao.queryVisitorsForEachDay();
			for(Object[] o : rawData){
				Integer cnt = Integer.valueOf(o[0].toString());//visitor counts
				String item = o[1].toString();//date
				result.add(new StatisticEntity(item,cnt));
			}
			return result;
		}
	
	public List<StatisticEntity> getHottestActionItem(){
		List<StatisticEntity> result = new ArrayList<>();
		List<Object[]> rawData = visitorDao.queryHottestActions();
		for(Object[] o : rawData){
			Integer cnt = Integer.valueOf(o[0].toString());//click counts
			String item = o[1].toString();//action item
			result.add(new StatisticEntity(item,cnt));
		}
		return result;
	}
	
	public Visitors addVisitorEntry(String actionItem, String ip, String token){
		Visitors v = new Visitors();
		v.setActionItem(actionItem);
		v.setCookieSession(token);
		v.setIp(ip);
		return visitorDao.save(v);
	}
	
	public Integer getVisitorsTotalCount(){
		List<Object[]> rawData = visitorDao.queryVisitorsTotalCount();
		return Integer.valueOf(rawData.get(0)[0].toString());
	}
}
