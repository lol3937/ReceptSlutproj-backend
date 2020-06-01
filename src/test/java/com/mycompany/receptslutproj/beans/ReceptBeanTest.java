/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.receptslutproj.beans;

import com.mycompany.receptslutproj.enteties.Recept;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


public class ReceptBeanTest {
    
    private ReceptBean receptBean;
    public ReceptBeanTest() {
    }
    
    @BeforeEach
    public void setup(){
        receptBean = new ReceptBean();
    }

    @Test
    public void testGetRecepts() throws Exception {
        System.out.println("getRecepts");
        Recept r= receptBean.getRecept(1);
        assertEquals(r.getName(),"Chockladmouse");  
    }


    
}
