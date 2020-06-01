/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.receptslutproj.resources;

import com.mycompany.receptslutproj.beans.IngrediensBean;
import com.mycompany.receptslutproj.beans.MängdBean;
import com.mycompany.receptslutproj.beans.ReceptBean;
import com.mycompany.receptslutproj.enteties.Ingrediens;
import com.mycompany.receptslutproj.enteties.Mängd;
import com.mycompany.receptslutproj.enteties.Recept;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
public class ReceptResource {

    @EJB
    ReceptBean receptBean;

    @EJB
    IngrediensBean ingrediensBean;
    
    /*@EJB
    MängdBean mängdBean;*/

    @GET
    @Path("recepts")
    public Response getRecepts() {
        List<Recept> recepts = receptBean.getRecepts();
        return Response.ok(recepts).build();
    }

    @GET
    @Path("recept")
    public Response getRecept(@QueryParam("id") int id) {
        Recept recept = receptBean.getRecept(id);
        return Response.ok(recept).build();
    }

    @GET
    @Path("ingredienser")
    public Response getIngrediens(@QueryParam("id") int id) {
        List<Ingrediens> ingredienser = ingrediensBean.getIngredienser(id);
        return Response.ok(ingredienser).build();
    }
    
    /*funkar inte. säger att mängdbean hittas inte
    @GET
    @Path("mängd")
    public Response getMängd(){
        List<Mängd> mängder = mängdBean.getMängder();
        return Response.ok(mängder).build();
    }*/

    @POST
    @Path("recept")
    public Response postRecept(Recept recept) {
        int result = receptBean.saveRecept(recept);
        if (result == 1) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("recept")
    public Response updateRecept(Recept recept) {
        int result = receptBean.updateRecept(recept);
        if (result == 1) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("recept")
    public Response deleteRecept(@QueryParam("id") int id) {
        int result = receptBean.deleteRecept(id);
        if (result == 1) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("ingrediens")
    public Response postIngrediens(@QueryParam("id") int id) {
        List<Ingrediens> ingrediens = ingrediensBean.getIngredienser(id);
        if (ingrediens != null) {
            return Response.ok(ingrediens).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
}
