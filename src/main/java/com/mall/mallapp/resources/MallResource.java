package com.mall.mallapp.resources;

import com.mall.mallapp.DTO.MallDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.model.Mall;
import com.mall.mallapp.service.MallService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.soap.Text;

import java.util.List;

@Path("/malls")
public class MallResource {

    MallService ms = new MallService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMalls(){

        try {
            List<MallDTO> mallList = ms.getAllMalls();

            return Response.ok(mallList.toArray(), MediaType.APPLICATION_JSON).build();
        }
        catch(NotFoundException ne)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ne.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

    }

    @GET
    @Path("/{mall_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMall(@PathParam("mall_id") int mall_id)
    {
        try {
            MallDTO mall = ms.getMall(mall_id);
            return Response.ok(mall.toJSON(), MediaType.APPLICATION_JSON).build();
        }
        catch (NotFoundException ne)
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
    public Response addMall(MallDTO mall)
    {
        try {
            MallDTO m = ms.add_Mall(mall);
            return Response.ok(m.toJSON(), MediaType.APPLICATION_JSON).build();
        }
        catch(IllegalArgumentException ie)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ie.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

    }

    @PUT
    @Path("/{mall_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMall(@PathParam("mall_id") int id ,  MallDTO mall)
    {
        try {
            ms.updateMall(id , mall);
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
    @Path("/{mall_id}")
    @Produces("text/plain")
    public Response deleteMall(@PathParam("mall_id") int id)
    {
        try {
            String massege = ms.deleteMall(id);
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

    @Path("/{mall_id}/floors")
    public FloorResource getFloorResource()
    {
        return new FloorResource();
    }

}
