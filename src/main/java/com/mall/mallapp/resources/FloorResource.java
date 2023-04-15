package com.mall.mallapp.resources;

import com.mall.mallapp.DTO.FloorDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.model.Floor;
import com.mall.mallapp.reposotry.FloorRepo;
import com.mall.mallapp.service.FloorService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class FloorResource {

    FloorService fs = new FloorService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFloors(@PathParam("mall_id") int mall_id)
    {
        try
        {
            List<FloorDTO> floorList = fs.getFloors(mall_id);
            return Response.ok(floorList.toArray(), MediaType.APPLICATION_JSON).build();
        }
        catch(NotFoundException ne)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ne.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFloor(@PathParam("mall_id") int mall_id ,  FloorDTO floor)
    {
        try {
            FloorDTO f = fs.add_Floor(mall_id, floor);
            return Response.ok(f.toJSON(), MediaType.APPLICATION_JSON).build();
        }
        catch (IllegalArgumentException ie)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ie.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

    }

    @GET
    @Path("/{floor_number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFloor(@PathParam("mall_id") int mall_id ,@PathParam("floor_number") int floor_number)
    {
        try {
            FloorDTO floor = fs.getFloor(mall_id , floor_number);
            return Response.ok(floor.toJSON(), MediaType.APPLICATION_JSON).build();

        }
        catch (Exception e)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @PUT
    @Path("/{floor_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFloor(@PathParam("mall_id") int mall_id ,@PathParam("floor_id") int id , FloorDTO floor)
    {
        try {
            fs.updateFloor(mall_id , id, floor);
            return Response.ok("updated successfully").build();
        }
        catch (IllegalArgumentException ie)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ie.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @DELETE
    @Path("/{floor_id}")
    @Produces("text/plain")
    public Response deleteFloor(@PathParam("floor_id") int id)
    {
        try
        {
            String massege = fs.deleteFloor(id);
            return Response.ok(massege).build();
        }
        catch (IllegalArgumentException ie)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ie.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Path("/{floor_id}/shops")
    public ShopResource GetShopeResource()
    {
        return new ShopResource();
    }
}
