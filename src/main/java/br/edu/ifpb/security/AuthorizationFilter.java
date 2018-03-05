package br.edu.ifpb.security;

import br.edu.ifpb.domain.UserIdentifier;
import br.edu.ifpb.service.LoginService;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;
import static javax.ws.rs.core.Response.*;

@Provider
public class AuthorizationFilter implements ContainerRequestFilter {

    @EJB
    private LoginService service;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        boolean signinPage = requestContext.getUriInfo().getPath().contains("signin");
        boolean loginPage = requestContext.getUriInfo().getPath().contains("login");

        if (signinPage || loginPage) {
            return;
        }

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || authHeader.isEmpty() || (! authHeader.contains("Basic ")) ) {
            requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity("{'msg':'unauthorized'}").build());
        }

        String withoutBasic = authHeader.replaceAll("Basic ", "");
        byte[] decode = Base64.getDecoder().decode(withoutBasic);

        String userWithPassword = new String(decode);
        StringTokenizer string = new StringTokenizer(userWithPassword, ":");

        String username = string.nextToken();
        String password = string.nextToken();

        UserIdentifier ident = service.login(username, password);

        if (ident == null) {
            requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity("{'msg':'unauthorized'}").build());
        }

    }

}
