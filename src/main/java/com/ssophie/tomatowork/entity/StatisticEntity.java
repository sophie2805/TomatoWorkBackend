package com.ssophie.tomatowork.entity;

public class StatisticEntity{

	String item;
	Integer cnt;
	public StatisticEntity(String item, Integer cnt) {
		super();
		this.item = item;
		this.cnt = cnt;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
}