package arek.nauka.messenger.service;

import java.util.List;
import java.util.Map;

import javax.persistence.Convert;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import arek.nauka.messenger.database.DatabaseClass;
import arek.nauka.messenger.exception.DataNotFoundException;
import arek.nauka.messenger.model.Message;

public class MessageService
{
	SessionFactory sessionFactory = null;
	Session session = null;
	List<Message> messagesfromSQL = null;

	public MessageService()
	{
		messagesfromSQL = new DatabaseClass().getAllMessages(); // ściągnij wszystkie wiadomości z SQL
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();

	}

	public List<Message> getAllMessages()
	{
		return messagesfromSQL;
	}

	public List<Message> getAllMessagesforYear(int year)
	{
		List<Message> messagesForYear = messagesfromSQL;
		Calendar cal = Calendar.getInstance();

		try
		{
			for (Message message : messagesForYear)
			{
				cal.setTime(message.getCreated());
				if (cal.get(Calendar.YEAR) == year)
				{
					messagesForYear.add(message);
				}
			}
			session.getTransaction().commit();
			session.close();

		}
		catch (Throwable th)
		{
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		}

		return messagesForYear;

	}

	public List<Message> getAllMessagesPaginated(int start, int size)
	{
		List<Message> list = messagesfromSQL;
		
		if (start + size > list.size())
			return new ArrayList<Message>();
		return list.subList(start, start + size);
	}

	public Message getMesage(Long Id)
	{
		int ID = Id.intValue();
		Message message = messagesfromSQL.get(ID);
		if (message == null)
		{
			throw new DataNotFoundException("Message with id " + Id + " not found");
		}
		return message;
	}

	public Message addMessage(Message message)
	{
		try
		{
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

	public Message updateMessage(Message message)
	{
		if (message.getId() <= 0)
			return null;

		message.setAuthor(message.getAuthor());
		message.setMessage(message.getMessage());
		message = (Message) session.get(Message.class, 1); 
		session.getTransaction().commit();
		session.close();
		return message;
	}

	public Message removeMessage(Long Id)
	{
		
		Session session ;
	    Message message ;

	    session = sessionFactory.getCurrentSession();
	    message = (Message)session.load(Message.class,Id);
	    session.delete(message);

	    //This makes the pending delete to be done
	    session.flush() ;
		return null;

	}

}
