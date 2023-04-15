package com.mall.mallapp.resources;

import com.mall.mallapp.DTO.ItemDTO;
import com.mall.mallapp.DTO.ShopDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.model.Item;
import com.mall.mallapp.model.Shop;
import com.mall.mallapp.service.ItemService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class ItemResource {

    ItemService is = new ItemService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems(@PathParam("shop_id") int shop_id) {
        try
        {
            List<ItemDTO> itemList = is.getItems(shop_id);
            return Response.ok(itemList.toArray(), MediaType.APPLICATION_JSON).build();
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
    public Response addItem(@PathParam("shop_id") int shop_id ,ItemDTO item)
    {
        try {
            ItemDTO i = is.add_item(shop_id, item);
            return Response.ok(i.toJSON(), MediaType.APPLICATION_JSON).build();
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
    @Path("/{item_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("item_id") int item_id)
    {
        try
        {
            ItemDTO i = is.getItem(item_id);
            return Response.ok(i.toJSON(), MediaType.APPLICATION_JSON).build();
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
    @Path("/{item_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("item_id") int item_id ,@PathParam("shop_id") int shop_id, ItemDTO item)
    {
        try
        {
            is.updateItem(item_id,shop_id ,item);
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
    @Path("/{item_id}")
    @Produces("text/plain")
    public Response deleteItem(@PathParam("item_id") int id)
    {
        try
        {
            String massege = is.deleteItem(id);
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

}
