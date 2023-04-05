package com.mall.mallapp.resources;

import com.mall.mallapp.model.Floor;
import com.mall.mallapp.model.Mall;
import com.mall.mallapp.service.FloorService;
import com.mall.mallapp.service.MallService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class FloorResource {

    FloorService fs = new FloorService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Floor> getFloors(@PathParam("mall_id") int mall_id){
        System.out.println("pppppppppp");
        return fs.getFloors(mall_id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Floor addFloor( Floor floor)
    {
        System.out.println("kkkkkkkkkkkkkkkkkkkkk");
        return fs.add_Floor(floor);
    }

    @GET
    @Path("/{floor_number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Floor getFloor(@PathParam("mall_id") int mall_id ,@PathParam("floor_number") int floor_number)
    {
        return fs.getFloor(mall_id , floor_number);
    }

    @PUT
    @Path("/{floor_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateMall(@PathParam("floor_id") int id , Floor floor)
    {
        fs.updateFloor(id , floor);
    }

    @DELETE
    @Path("/{floor_id}")
    @Produces("text/plain")
    public String deleteMall(@PathParam("floor_id") int id)
    {
        return fs.deleteFloor(id);
    }

}
