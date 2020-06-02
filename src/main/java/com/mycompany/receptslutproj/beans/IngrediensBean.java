/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.receptslutproj.beans;

import com.mycompany.receptslutproj.ConnectionFactory;
import com.mycompany.receptslutproj.enteties.Ingrediens;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class IngrediensBean {
    //Metod för att hämta alla ingredienser
    public List<Ingrediens> getIngredienser(int id) {
        List<Ingrediens> ingredienser = new ArrayList<>();
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = "SELECT ingredienser.id, mängd, ingredienser.name FROM recept_has_ingrediens, ingredienser WHERE ingredienser.id = ingrediens_id AND recept_id = " +id;
            ResultSet data = stmt.executeQuery(sql);
            while (data.next()) {
                int ingrediensid = data.getInt("id");
                String name = data.getString("name");
                String mängd = data.getString("mängd");
                Ingrediens ingrediens = new Ingrediens(ingrediensid,name,mängd);
                ingredienser.add(ingrediens);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return ingredienser;
    }

}
