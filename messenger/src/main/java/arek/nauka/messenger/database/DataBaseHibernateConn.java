package arek.nauka.messenger.database;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import arek.nauka.messenger.model.Message;

public class DataBaseHibernateConn
{
	SessionFactory sessionFactory = null;
	Session session = null;

	//private static final SessionFactory sessionFactory = null;

	public DataBaseHibernateConn()
	{
		//		System.out.println("przed utworzeniem konfiguracji");
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		//		session = sessionFactory.openSession();
		//		System.out.println("po  utworzeniem session");

	}

	public Message saveMessage(Message message)
	{
		try
		{
			System.out.println("przed utworzeniem session save");
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(message);
			session.getTransaction().commit();
			session.close();

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.toString());
			session.getTransaction().rollback();
		}
		return message;

	}

	public List<Message> getAllMessages()
	{
		List<Message> messages = new ArrayList<Message>();
		try
		{
			
			System.out.println("wszedł w try");
			sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
		    System.out.println("po konfiguracji");
			session.beginTransaction();
			Query<Message> query = session.createQuery("From Message");

			messages = query.getResultList();
			session.getTransaction().commit();
			session.close();

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Błąd " + ex.toString());
			session.getTransaction().rollback();
		}
		//return new ArrayList<Message>(messages1.values());
		return messages;

	}

}
