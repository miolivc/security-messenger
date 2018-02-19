
package br.edu.ifpb.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {
    
    @POST
    public Response sigin() {
        return Response.ok().build();
    }
    
}
