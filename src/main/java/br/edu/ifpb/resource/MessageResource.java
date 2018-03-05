package br.edu.ifpb.resource;

import br.edu.ifpb.domain.MessageDTO;
import br.edu.ifpb.service.MessageService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.PublicKey;
import java.util.List;

@Path("message")
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    @EJB
    private MessageService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessagesBy(PublicKey receiver) {

        List<MessageDTO> messages = service.getAllMessages(receiver);
        if (messages == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(messages).build();
    }

    @POST
    public Response sendMessage(MessageDTO message) {
        service.saveMessage(message);
        return Response.created(null).build();
    }
}
