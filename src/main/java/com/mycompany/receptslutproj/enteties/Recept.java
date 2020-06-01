/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.receptslutproj.enteties;

import java.util.ArrayList;
import java.util.List;


public class Recept {
    private int id;
    private String name;
    private String instruction;
    private List<Ingrediens> ingrediens = new ArrayList();

    public Recept() {
        
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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List getIngrediens() {
        return ingrediens;
    }

    public void setIngrediens(List ingrediens) {
        this.ingrediens = ingrediens;
    }

    public Recept(int id, String name, String instruction) {
        this.id = id;
        this.name = name;
        this.instruction = instruction;
    }
    
    

    
    
    

    
}

