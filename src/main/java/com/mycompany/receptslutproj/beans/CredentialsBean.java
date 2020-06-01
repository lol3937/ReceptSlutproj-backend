package com.mycompany.receptslutproj.beans;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.receptslutproj.ConnectionFactory;
import com.mycompany.receptslutproj.enteties.Credentials;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.Logger;
import javax.ejb.Stateless;



@Stateless
public class CredentialsBean {

    //private static final Logger LOGGER = LoggerFactory.getLogger(CredentialsBean.class);

    
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
        return false;
    }

    /*private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    
    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public boolean verifyToken(String token) {;
        try ( Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE token = ?");
            stmt.setString(1, token);
            ResultSet data = stmt.executeQuery();
            if (data.next()) {
                return true;
            }
        } catch (Exception e) {
            
        }
        return false;
    }

    //Lägger till användre i dtb?
    private int addToken(Credentials credentials, String token) {
        try ( Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE user SET token = ? WHERE username = ?");
            stmt.setString(1, token);
            stmt.setString(2, credentials.getUsername());
            return stmt.executeUpdate();
        } catch (Exception e) {
            
        }
        return 0;
    }

    //Kollar om user/password är rätt
    public String checkCredentials(Credentials credentials) {
        String token = "";
        try ( Connection connection = ConnectionFactory.getConnection()) {
            String hashedPassword = "";
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            stmt.setString(1, credentials.getUsername());
            ResultSet data = stmt.executeQuery();
            if (data.next()) {
                hashedPassword = data.getString("hash");
            }
            if (BCrypt.verifyer().verify(credentials.getPassword().toCharArray(), hashedPassword).verified) {
                token = generateNewToken();
                addToken(credentials, token);
            }
        } catch (Exception e) {
            
        }
        return token;

    }*/

    public int saveCredentials(Credentials credentials) {
        try ( Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO user (username, hash) VALUES(?, ?)");
            stmt.setString(1, credentials.getUsername());
            String hashedPassword = BCrypt.withDefaults().hashToString(13, credentials.getPassword().toCharArray());
            stmt.setString(2, hashedPassword);
            return stmt.executeUpdate();
        } catch (Exception e) {
            
        }
        return 0;
    }
}
