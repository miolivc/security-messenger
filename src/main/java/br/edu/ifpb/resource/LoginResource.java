
package br.edu.ifpb.resource;

import br.edu.ifpb.domain.UserIdentifier;
import br.edu.ifpb.service.LoginService;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {

    @EJB
    private LoginService service;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@PathParam("username") String username,
            @PathParam("password") String password) {

        UserIdentifier ident = service.login(username, password);

        if (ident == null) {
            Response.status(Response.Status.NOT_FOUND)
                    .entity("{'msg':'user not found'}")
                    .build();
        }

        return Response.ok().entity(ident.getSecretKey().getEncoded()).build();
    }

}
