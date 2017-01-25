package com.pancake.entity;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.pancake.util.HibernateUtil;

public class Many2oneTest {

	@Test
	public void testSave() {
		Grade grade = new Grade("gname03", "gdesc03");
		Student student1 = new Student("student01", "male");
		Student student2 = new Student("student02", "female");
		
		//设置双向多对一（一对多）关联关系
		student1.setGrade(grade);
		student2.setGrade(grade);
		grade.getStudents().add(student1);
		grade.getStudents().add(student2);
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(grade);
//		session.save(student1);
//		session.save(student2);
		transaction.commit();
		HibernateUtil.closeSession(session);
	}

}
