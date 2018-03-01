package br.edu.ifpb.resource;

import br.edu.ifpb.domain.UserIdentifier;
import br.edu.ifpb.service.CredentialService;
import br.edu.ifpb.service.LoginService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
public class UserResource {

    @EJB
    private CredentialService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserIdents() {

        List<UserIdentifier> all = service.getAllCredential();

        if (all == null) {
            Response.status(Response.Status.NOT_FOUND).entity("{'msg':'not found'}").build();
        }
        GenericEntity<UserIdentifier> answer = new GenericEntity(all){};

        return Response.ok().entity(answer).build();
    }

}
