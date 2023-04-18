package com.mall.mallapp.resources;
import com.mall.mallapp.dto.ItemDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.service.ItemService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/**
 * Resource class for handling requests related to items in a shop.
 */
public class ItemResource {

    private ItemService is = new ItemService();
    /**
     * Retrieves a list of items for a given shop.
     *
     * @param shop_id the id of the shop to retrieve items for
     * @return a response containing the list of items in JSON format
     */
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

    /**
     * Adds a new item to a given shop.
     *
     * @param shop_id the id of the shop to add the item to
     * @param item    the item to add
     * @return a response containing the added item in JSON format
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(@PathParam("shop_id") int shop_id ,ItemDTO item)
    {
        try {
            ItemDTO i = is.addItem(shop_id, item);
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

    /**
     * Retrieves a single item for a given id.
     *
     * @param item_id the id of the item to retrieve
     * @return a response containing the item in JSON format
     */
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

    /**
     * Updates an existing item for a given id and shop id.
     *
     * @param item_id the id of the item to update
     * @param shop_id the id of the shop to update the item for
     * @param item    the updated item
     * @return a response indicating a successful update
     */
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

    /**
     * This resource class handles HTTP DELETE requests for deleting an item from a shop in the mall application.
     * @param id for the item to be deleted
     * @return Response object with a success message in JSON format, if successful.
     */
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
