
package br.edu.ifpb.resource;

import br.edu.ifpb.domain.UserIdentifier;
import br.edu.ifpb.service.LoginService;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {

    @EJB
    private LoginService service;

    @POST
    public Response login(String username, String password) {

        UserIdentifier ident = service.login(username, password);

        if (ident == null) {
            Response.status(Response.Status.NOT_FOUND).entity("{'msg':'user not found'}").build();
        }

        return Response.ok().entity(ident.getSecretKey()).build();
    }

}
