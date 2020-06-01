package com.mycompany.receptslutproj.filters;

import com.mycompany.receptslutproj.ConnectionFactory;
import com.mycompany.receptslutproj.beans.CredentialsBean;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    //private static final Logger LOGGER = LoggerFactory.getLogger(CredentialsBean.class);
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        /*if (!request.getMethod().equals("GET")) {
            if (request.getHeaders().containsKey("Authorization") && !request.getHeaderString("Authorization").equals("")) {
                String token = request.getHeaderString("Authorization");
                try ( Connection connection = ConnectionFactory.getConnection()) {
                    PreparedStatement stmt = connection.prepareStatement("SELECT id FROM user WHERE token = ?");
                    stmt.setString(1, token);
                    ResultSet data = stmt.executeQuery();
                    if (!data.next()) {
                        request.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    }
                } catch (Exception e) {

                }
            } else {
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }*/
    }
}
