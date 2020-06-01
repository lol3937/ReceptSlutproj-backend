/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.receptslutproj.enteties;


public class Ingrediens {
    private int id;
    private String name;
    private String mängd;

    public Ingrediens(int id, String name, String mängd) {
        this.id = id;
        this.name = name;
        this.mängd = mängd;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMängd() {
        return mängd;
    }

    public void setMängd(String mängd) {
        this.mängd = mängd;
    }

    



    
}
