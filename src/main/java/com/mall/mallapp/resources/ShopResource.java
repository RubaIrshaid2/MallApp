/**
 * This class represents a resource for managing shops in a mall.
 *
 */
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

    /**
     * Retrieves a list of shops on a specified floor of a mall.
     *
     * @param mall_id The ID of the mall.
     * @param floor_id The ID of the floor.
     * @return A Response object containing a list of ShopDTO objects in JSON format, with a success status code.
     *         If the request is unsuccessful, the Response object contains an error message and an appropriate status code.
     */

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

    /**
     * Retrieves a list of shops on a specified floor of a mall.
     *
     * @param mall_id The ID of the mall.
     * @param floor_id The ID of the floor.
     * @return A Response object containing a list of ShopDTO objects in JSON format, with a success status code.
     *         If the request is unsuccessful, the Response object contains an error message and an appropriate status code.
     */
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
    /**
     * Retrieves a specified shop on a specified floor of a mall.
     *
     * @param mall_id The ID of the mall.
     * @param floor_id The ID of the floor.
     * @param shop_id The ID of the shop.
     * @return A Response object containing the ShopDTO object in JSON format, with a success status code.
     *         If the request is unsuccessful, the Response object contains an error message and an appropriate status code.
     */
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

    /**
     *
     *
     * Updates the information of a shop with the given ID.
     * @param shop_id The ID of the shop to update.
     * @param shop The new shop information to update with.
     * @return A response indicating the success or failure of the update.
     */
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

    /**
     *
     * Deletes the shop with the given ID.
     * @param id The ID of the shop to delete.
     * @return A response indicating the success or failure of the delete operation.
     */
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

    /**
     * Gets the sub-resource for managing the items of a shop with the given ID.
     * @return An instance of the {@link ItemResource} class for managing the items of the shop.
     */
    @Path("/{shop_id}/items")
    public ItemResource GetItemResource()
    {
        return new ItemResource();
    }
}
