
package br.edu.ifpb.resource;

import br.edu.ifpb.domain.UserIdentifier;
import br.edu.ifpb.service.LoginService;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {

    @EJB
    private LoginService service;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username,
            @FormParam("password") String password) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        UserIdentifier ident = service.login(username, password);

        if (ident == null) {
            Response.status(Response.Status.NOT_FOUND)
                    .entity("{'msg':'user not found'}")
                    .build();
        }

        return Response.ok().entity(ident.getSecretKey()).build();
    }

}
