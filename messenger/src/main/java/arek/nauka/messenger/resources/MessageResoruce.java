package arek.nauka.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import arek.nauka.messenger.model.Message;
import arek.nauka.messenger.resources.beans.MessageFilterBean;
import arek.nauka.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResoruce
{
	MessageService messageService = new MessageService();

	//Sczytaj
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean ) 
	{
		if(filterBean.getYear() >0)
		{
			return messageService.getAllMessagesforYear(filterBean.getYear());
		}
		if (filterBean.getStart() >=0 && filterBean.getSize() >=0)
		{
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
			
		return messageService.getAllMessages();
	}

	//Utwórz
	@POST
	public Message addMessage(Message message)
	{
		return messageService.addMessage(message);
	}
	
	//Update konkretnego ID wziętego z URL
	@PUT
	@Path("/{messageID}")
	public Message updateMessage(@PathParam("messageID") Long ID, Message message)
	{
		message.setId(ID);
		return messageService.updateMessage(message);
	}
	
	// usuwanie konrketnej wiadomości
	@DELETE
	@Path("/{messageID}")
	public Message deleteMessage(@PathParam("messageID") Long ID)
	{
		return messageService.removeMessage(ID);
	}

	//Sczytaj pojedyncza wiadomosc
	@GET
	@Path("/{messageID}") // informacja do jersey - co mapujesz w messageID, przekaż to za pomocą @PathParam do środka do zmiennej Long Id
	public Message getMessage(@PathParam("messageID") Long ID)
	{
		return messageService.getMesage(ID);
	}
}
