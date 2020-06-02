package com.mycompany.receptslutproj.beans;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.receptslutproj.ConnectionFactory;
import com.mycompany.receptslutproj.enteties.Credentials;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.Base64;
import java.util.Properties;
//import java.util.logging.Logger;
import javax.ejb.Stateless;



@Stateless
public class CredentialsBean {

    //private static final Logger LOGGER = Logger.getLogger(name).getLogger(CredentialsBean.class);

    public String getProperty(String key) throws IOException {
        try {
            
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream("props.properties");

        Properties properties = new Properties();
        properties.load(is);

        return properties.getProperty(key);
        } catch (Exception e) {
            return "error";
        }
        }
    
     public Credentials createCredentials(String basicAuth) {
        basicAuth = basicAuth.substring(6).trim();
        byte[] bytes = Base64.getDecoder().decode(basicAuth);
        basicAuth = new String(bytes);
        int colonPos = basicAuth.indexOf(":");
        String username = basicAuth.substring(0, colonPos);
        String password = basicAuth.substring(colonPos + 1);
        return new Credentials(username, password);
    }

    public boolean checkCredentials(Credentials credentials) {
        if (credentials.getUsername().equals("Nisse") && 
            credentials.getPassword().equals("Secret")) {
            return true;
        }
        if(credentials.getUsername().equals("Felix") && 
            credentials.getPassword().equals("AnotherOne")) {
            return true;
        }
        return false;
    }
    
    public int saveCredentials(Credentials credentials) {
        try ( Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO user (username, hash) VALUES(?, ?)");
            stmt.setString(1, credentials.getUsername());
            String hashedPassword = BCrypt.withDefaults().hashToString(13, credentials.getPassword().toCharArray());
            stmt.setString(2, hashedPassword);
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("error in saveCred");
        }
        return 0;
    }
}
