package arek.nauka.messenger.database;

import java.util.List;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import arek.nauka.messenger.model.Message;

public class DataBaseHibernateConn
{
	//SessionFactory sessionFactory = null;
	Session session = null;

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory()
	{
		try
		{
			// Create the SessionFactory from hibernate.cfg.xml
			return new AnnotationConfiguration().configure().buildSessionFactory();

		}
		catch (Throwable ex)
		{
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public static void shutdown()
	{
		// Close caches and connection pools
		getSessionFactory().close();
	}

	public DataBaseHibernateConn()
	{
		System.out.println("przed utworzeniem konfiguracji");

		//sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		getSessionFactory();
		System.out.println("po  utworzeniem session");
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
		System.out.println("lista załadowana");
		List<Message> messages = new ArrayList<Message>();
		try
		{
			System.out.println("wszedł w try");
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query<Message> query = session.createQuery("From Message");

			messages = query.getResultList();
			session.getTransaction().commit();
			session.close();

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.toString());
			session.getTransaction().rollback();
		}
		return messages;

	}

}
