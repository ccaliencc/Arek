package com.Arek.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main
{

	public static void main(String[] args)
	{
	
		Student student = new Student();
		
		student.setStudent("Arek1");
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(student);
		student.setStudent("Arek updated2");
//		student = (Student) session.get(Student.class, 1); // 1 oznacza ID numer który chcemy zmienić
//		System.out.println("Student Object having student name as: " + student.getStudent());
//		
		
//		
		//Przekazuje wybrany rekord z tabeli zap omocą get(Stduent.class, 1) do dalszej obróbki, czy to update czy delete
//		session.update(student);
		//session.delete(student);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

	}

}
