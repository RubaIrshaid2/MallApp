package com.mall.mallapp.resources;

import com.mall.mallapp.model.Floor;
import com.mall.mallapp.model.Mall;
import com.mall.mallapp.service.FloorService;
import com.mall.mallapp.service.MallService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class FloorResource {

    FloorService fs = new FloorService();
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Floor> getFloors(@PathParam("mall_id") int mall_id){
        return fs.getFloors(mall_id);
    }

    @GET
    @Path("/{floor_number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Floor getFloor(@PathParam("mall_id") int mall_id ,@PathParam("floor_number") int floor_number)
    {
        return fs.getFloor(mall_id , floor_number);
    }
}
