package com.pancake.entity;

import static org.junit.Assert.*;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.pancake.util.HibernateUtil;

public class GradeTest {

	@Test
	public void testAddStudent2Grade() {
		// 将学生添加到班级
		Grade grade = new Grade("gname01", "gdesc01");
		Student student1 = new Student("student01", "male");
		Student student2 = new Student("student02", "female");

		// 如果希望在学生表中添加对应的学生编号，需要在班级中添加学生，建立关联关系
		grade.getStudents().add(student1);
		grade.getStudents().add(student2);

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(grade);
		session.save(student1);
		session.save(student2);
		transaction.commit();
		HibernateUtil.closeSession(session);
	}

	@Test
	public void testFoundStudentByGrade() {
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		Grade grade = (Grade) session.get(Grade.class, 1);
		System.out.println(grade);
		
		Set<Student> students = grade.getStudents();
		for (Student student : students) {
			System.out.println(student);
		}
		
		transaction.commit();
		HibernateUtil.closeSession(session);
	}
	
	@Test
	public void testUpdateStudentByGrade() {
		
		Grade grade = new Grade("gname02", "gdesc02");
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		Student student2 = (Student) session.get(Student.class, 2);
		System.out.println(student2);
		
		grade.getStudents().add(student2);
		session.save(grade);

		transaction.commit();
		HibernateUtil.closeSession(session);
	}
	
	@Test
	public void testDelete() {
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		Student student = (Student) session.get(Student.class, 2);
		System.out.println(student);
		
		session.delete(student);
		
		transaction.commit();
		HibernateUtil.closeSession(session);
	}
}
