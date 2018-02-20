package br.edu.ifpb.resource;

import br.edu.ifpb.domain.User;
import br.ifpb.edu.exception.UserException;
import br.ifpb.edu.service.LoginService;
import java.net.URI;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("login")
public class LoginResource {

    @EJB
    private LoginService service;

    @POST
    @Path("signin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Context UriInfo info, User user) {
        try {
            service.sigin(user.getUsername(), user.getPassword(), user.getName());
        } catch(UserException ex) {
            return Response.status(401).entity(ex).build();
        }
        
        URI location = info.getAbsolutePathBuilder()
                           .path(user.getUsername())
                           .build();
        return Response.created(location).build();
    }
    
}
