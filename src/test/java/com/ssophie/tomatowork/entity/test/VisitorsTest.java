package com.ssophie.tomatowork.entity.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ssophie.tomatowork.entity.Visitors;

public class VisitorsTest {
	
	private static Session session;
	
	@BeforeClass
	public static void getSession(){
		session = HibernateUtil.getSession();
	}
	
	@Test
	public void TestAddCase() {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Visitors visitor = new Visitors();
			visitor.setIp("xxx.xxx.xxx");
			visitor.setCookieSession("abcjdkfhkfjelskfmdjgrhgjs");
			visitor.setActionItem("duration-combo-box");
			session.save(visitor);
			transaction.commit();
		} catch (Exception exception) {
System.out.println(exception.getMessage());
			transaction.rollback();
		}
	}
	
	@AfterClass
	public static void closeSession(){
		if(session != null)
			session.close();
	}
}

