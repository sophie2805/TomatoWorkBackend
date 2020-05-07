package com.ssophie.tomatowork.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="visitor_info")
public class Visitors {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="cookie_session")
	private String cookieSession;
	
	@Column(name="ip")
	private String ip;

	@Column(name="action_item")
	private String actionItem;

	@Column(name="create_at", insertable=false, columnDefinition ="deafult CURRENT_TIMESTAMP")
	private Timestamp createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCookieSession() {
		return cookieSession;
	}

	public void setCookieSession(String cookieSession) {
		this.cookieSession = cookieSession;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getActionItem() {
		return actionItem;
	}

	public void setActionItem(String actionItem) {
		this.actionItem = actionItem;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
}
