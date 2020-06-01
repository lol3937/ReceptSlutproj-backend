/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.receptslutproj.beans;

//Huvudklassen
import com.mycompany.receptslutproj.ConnectionFactory;
import com.mycompany.receptslutproj.enteties.Recept;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.ejb.Stateless;

@Stateless
public class ReceptBean {

    //hämtar alla recept
    public List<Recept> getRecepts() {
        List<Recept> recepts = new ArrayList<>();
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM recept";
            ResultSet data = stmt.executeQuery(sql);
            while (data.next()) {
                int id = data.getInt("id");
                String name = data.getString("name");
                String instruction = data.getString("instruction");
                Recept recept = new Recept(id, name, instruction);
                recepts.add(recept);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return recepts;
    }

    //hämtar specifikt ett recept
    public Recept getRecept(int id) {
        Recept recept = new Recept();
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM recept WHERE id= " + id;
            ResultSet data = stmt.executeQuery(sql);
            while (data.next()) {
                String name = data.getString("name");
                String instruction = data.getString("instruction");
                recept = new Recept(id, name, instruction);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return recept;
    }

    //Spara ett recept
    public int saveRecept(Recept recept) {
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = String.format("INSERT INTO recept (`name`, `id`, `instruction`) "
                    + "VALUES('%s',NULL,'%s')", recept.getName(), recept.getId(), recept.getInstruction());
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
        }
        return 0;

    }

    //Ändra ett recept
    public int updateRecept(Recept recept) {
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = String.format("UPDATE recept SET name = '%s', instruction= '%s', id= '%d' WHERE id=%d", recept.getName(), recept.getInstruction(), recept.getId());
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;

    }

    public int deleteRecept(int id) {
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = String.format("DELETE FROM recept WHERE id = %d", id);
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }

    
        
}
