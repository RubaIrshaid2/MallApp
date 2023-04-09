package com.mall.mallapp.resources;

import com.mall.mallapp.model.Item;
import com.mall.mallapp.model.Shop;
import com.mall.mallapp.service.ItemService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class ItemResource {

    ItemService is = new ItemService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getItems(@PathParam("shop_id") int shop_id) {
        return is.getItems(shop_id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Item addItem(@PathParam("shop_id") int shop_id ,Item item)
    {
        return is.add_item(shop_id ,item);
    }

    @GET
    @Path("/{item_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItem(@PathParam("item_id") int item_id)
    {
        return is.getItem(item_id);
    }

    @PUT
    @Path("/{item_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateItem(@PathParam("item_id") int item_id , Item item)
    {
        is.updateItem(item_id,item);
    }

    @DELETE
    @Path("/{item_id}")
    @Produces("text/plain")
    public String deleteItem(@PathParam("item_id") int id)
    {
        return is.deleteItem(id);
    }

}
