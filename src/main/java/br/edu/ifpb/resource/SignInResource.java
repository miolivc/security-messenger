package br.edu.ifpb.resource;

import br.edu.ifpb.domain.User;
import br.edu.ifpb.exception.UserException;
import br.edu.ifpb.service.LoginService;
import java.net.URI;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("signin")
public class SignInResource {

    @EJB
    private LoginService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Context UriInfo info, User user) {
        try {
            service.signIn(user.getUsername(), user.getPassword(), user.getName());
        } catch(UserException ex) {
            return Response.status(401).entity(ex).build();
        }

//        URI location = info.getAbsolutePathBuilder()
//                           .path(user.getUsername())
//                           .build();

        return Response.created(null).build();
    }
    
}
