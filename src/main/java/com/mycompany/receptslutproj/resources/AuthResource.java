package com.mycompany.receptslutproj.resources;

import com.mycompany.receptslutproj.beans.CredentialsBean;
import com.mycompany.receptslutproj.enteties.Credentials;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path("auth")
public class AuthResource {

    @EJB
    CredentialsBean credentialsBean;

    
    @GET
    @Path("{key")
    public Response getKey(PathParam ("key") String key){
        return Response.ok(credentialsBean.getProperty(key)).build();
    }
    
    /*@GET
    public Response checkUser(@HeaderParam("authorization") String basicAuth) {
        Credentials credentials = credentialsBean.createCredentials(basicAuth);
        String token = credentialsBean.checkCredentials(credentials);
        if (!token.equals("")) {
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }*/

    
    @Path("/token")
    @GET
    public Response verifyUser(@HeaderParam("authorization") String token) {
        if (credentialsBean.verifyToken(token)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    
    @Path("/create")
    @GET
    public Response createUser(@HeaderParam("authorization") String basicAuth) {
        Credentials credentials = credentialsBean.createCredentials(basicAuth);
        if (credentialsBean.saveCredentials(credentials) == 1) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
