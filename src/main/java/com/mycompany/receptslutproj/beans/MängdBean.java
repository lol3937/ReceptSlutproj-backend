
package com.mycompany.receptslutproj.beans;

import com.mycompany.receptslutproj.ConnectionFactory;
import com.mycompany.receptslutproj.enteties.Mängd;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MängdBean {

    public List<Mängd> getMängder() {
        List<Mängd> Mängder = new ArrayList<>();
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM mängd";
            ResultSet data = stmt.executeQuery(sql);
            while (data.next()) {
                int id = data.getInt("id");
                String name = data.getString("name");
                Mängd mängd = new Mängd(id, name);
                Mängder.add(mängd);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return Mängder;
    }

    public Mängd getMängd(int id) {
        Mängd mängd = new Mängd();
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM mängd WHERE id= " + id;
            ResultSet data = stmt.executeQuery(sql);
            while (data.next()) {
                String name = data.getString("name");
                mängd = new Mängd(id, name);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return mängd;
    }

    public int saveMängd(Mängd mängd) {
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = String.format("INSERT INTO mängd VALUES(NULL, '%s'", mängd.getName());
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
        }
        return 0;

    }

    public int updateMängd(Mängd mängd) {
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = String.format("UPDATE mängd SET name = '%s', '%s' WHERE id=%d", mängd.getName(), mängd.getId());
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;

    }

    public int deleteMängd(int id) {
        try ( Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = String.format("DELETE FROM mängd WHERE id = %d", id);
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }
}
