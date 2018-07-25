package com.Arek.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import net.bytebuddy.description.ModifierReviewable.OfAbstraction;


public class Main
{

	public static void main(String[] args)
	{

		//		Student student = new Student();
		//		
		//		student.setStudent("Arek1");
		//		
		//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		//		Session session = sessionFactory.openSession();
		//		session.beginTransaction();
		//
		//		session.save(student);
		//		student.setStudent("Arek updated2");
		////		student = (Student) session.get(Student.class, 1); // 1 oznacza ID numer który chcemy zmienić
		////		System.out.println("Student Object having student name as: " + student.getStudent());
		////		
		//		
		////		
		//		//Przekazuje wybrany rekord z tabeli zap omocą get(Stduent.class, 1) do dalszej obróbki, czy to update czy delete
		////		session.update(student);
		//		//session.delete(student);
		//		session.getTransaction().commit();
		//		session.close();
		//		sessionFactory.close();

		List<Student> messages = new ArrayList<Student>();

		try
		{
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			
			Query<Student> query = session.createQuery("From Student");

			messages = query.list();
			session.getTransaction().commit();
			session.close();
			for (Student s : messages)
				System.out.println(s.getStudent());

		}
		catch (Throwable th)
		{
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);

		}
		
	}

	//			try
	//			{
	//				
	//				System.out.println("wszedł w try");
	//				sessionFactory = new Configuration().configure().buildSessionFactory();
	//				Session session = sessionFactory.openSession();
	//			    System.out.println("po konfiguracji");
	//				session.beginTransaction();
	//				Query<Message> query = session.createQuery("From Message");
	//
	//				messages = query.getResultList();
	//				session.getTransaction().commit();
	//				session.close();
	//
	//			}
	//			catch (Exception ex)
	//			{
	//				ex.printStackTrace();
	//				System.out.println("Błąd " + ex.toString());
	//				session.getTransaction().rollback();
	//			}
	//return new ArrayList<Message>(messages1.values());

}
