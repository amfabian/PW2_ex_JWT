package dev.amf;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/sum")
public class BFF {
    private static final Logger LOGGER = Logger.getLogger(BFF.class.getName());
    
/* Recuperando uma informação do token */
@Inject
@Claim(standard = Claims.full_name)
String fullName;


    //REST Client
    @Inject
    @RestClient
    BackendClient backend;

    @Path("/{a}/{b}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"Admin"})
    public int getSUM(@PathParam("a") int a, @PathParam("b") int b) {
        LOGGER.log(Level.INFO, "BFF: {0}", fullName);
        System.out.println(a+b);
        return backend.getSum(a, b);
    
}
}