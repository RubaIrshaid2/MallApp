package com.mall.mallapp.resources;

import com.mall.mallapp.model.Floor;
import com.mall.mallapp.model.Shop;
import com.mall.mallapp.service.ShopService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//@Path("/")
public class ShopResource {

    ShopService ss = new ShopService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shop> getShops(@PathParam("mall_id") int mall_id , @PathParam("floor_id") int floor_id)
    {
        return ss.getShops(mall_id,floor_id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Shop addShop(@PathParam("mall_id") int mall_id , @PathParam("floor_id") int floor_id , Shop shop)
    {
        return ss.add_shop(mall_id , floor_id , shop);
    }

    @GET
    @Path("/{shop_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shop getShop(@PathParam("shop_id") int shop_id)
    {
        return ss.getShop(shop_id);
    }

    @PUT
    @Path("/{shop_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateShop(@PathParam("shop_id") int shop_id , Shop shop)
    {
        ss.updateShop(shop_id,shop);
    }

    @DELETE
    @Path("/{shop_id}")
    @Produces("text/plain")
    public String deleteShop(@PathParam("shop_id") int id)
    {
        return ss.deleteShop(id);
    }

    @Path("/{shop_id}/items")
    public ItemResource GetItemResource()
    {
        return new ItemResource();
    }
}
