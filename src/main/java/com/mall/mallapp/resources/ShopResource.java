package com.mall.mallapp.resources;

import com.mall.mallapp.DTO.ShopDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.model.Floor;
import com.mall.mallapp.model.Shop;
import com.mall.mallapp.service.ShopService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//@Path("/")
public class ShopResource {

    ShopService ss = new ShopService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShops(@PathParam("mall_id") int mall_id , @PathParam("floor_id") int floor_id)
    {
        try
        {
            List<ShopDTO> shopList = ss.getShops(mall_id,floor_id);
            return Response.ok(shopList.toArray(), MediaType.APPLICATION_JSON).build();
        }
        catch (IllegalArgumentException ie)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ie.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
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
    public Response addShop(@PathParam("mall_id") int mall_id , @PathParam("floor_id") int floor_id , ShopDTO shop)
    {
        try{
            ShopDTO s = ss.add_shop(mall_id , floor_id , shop);
            return Response.ok(s.toJSON(), MediaType.APPLICATION_JSON).build();
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
    @Path("/{shop_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShop(@PathParam("mall_id") int mall_id, @PathParam("floor_id") int floor_id , @PathParam("shop_id") int shop_id)
    {
        try{
            ShopDTO s = ss.getShop(mall_id , floor_id ,shop_id);
            return Response.ok(s.toJSON(), MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @PUT
    @Path("/{shop_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateShop(@PathParam("shop_id") int shop_id , ShopDTO shop)
    {
        try
        {
            ss.updateShop(shop_id,shop);
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
    @Path("/{shop_id}")
    @Produces("text/plain")
    public Response deleteShop(@PathParam("shop_id") int id)
    {
        try
        {
            String massege = ss.deleteShop(id);
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

    @Path("/{shop_id}/items")
    public ItemResource GetItemResource()
    {
        return new ItemResource();
    }
}
