package com.mall.mallapp.resources;

import com.mall.mallapp.dto.MallDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.exception.ObjectExistsException;
import com.mall.mallapp.service.MallService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *  The MallResource class represents a RESTful web service for managing malls.
 *  It provides endpoints for retrieving, adding, updating, and deleting malls,
 *  as well as an endpoint for accessing the floors of a specific mall.
 */
@Path("/malls")
public class MallResource {

    private MallService ms = new MallService();

    /**
     * Retrieves a list of all malls in the database.
     *
     * @return A Response object containing a JSON array of MallDTOs
     *         representing all the malls in the database, or a NOT_FOUND
     *         status code and an error message if no malls are found.
     */
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

    /**
     * Retrieves the details of a specific mall.
     *
     * @param mall_id The ID of the mall to retrieve.
     * @return A Response object containing a JSON representation of the
     *         MallDTO for the specified mall, or a NOT_FOUND status code
     *         and an error message if the specified mall does not exist.
     */
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

    /**
     * Adds a new mall to the database.
     *
     * @param mall The MallDTO representing the new mall to add.
     * @return A Response object containing a JSON representation of the
     *         MallDTO for the newly-added mall, or a NOT_FOUND status code
     *         and an error message if the mall could not be added
     *         if its already exist or there is missing data.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMall(MallDTO mall)
    {
        try {
            MallDTO m = ms.addMall(mall);
            return Response.ok(m.toJSON(), MediaType.APPLICATION_JSON).build();
        }
        catch(IllegalArgumentException ie)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ie.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        catch (ObjectExistsException oe)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(oe.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

    }

    /**
     * Updates the details of an existing mall in the database.
     *
     * @param id The ID of the mall to update.
     * @param mall The MallDTO representing the updated details of the mall.
     * @return A Response object indicating whether the update was successful,
     *         or a NOT_FOUND status code and an error message if the mall does
     *         not exist.
     */
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

    /**
     * Deletes a mall with the given ID from the system.
     * @param id the ID of the mall to delete
     * @return a plain text response indicating the success or failure of the operation
     */
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

    /**
     * Returns a new instance of the FloorResource for the mall with the given ID.
     * @return a new instance of the FloorResource for the mall with the given ID
     */
    @Path("/{mall_id}/floors")
    public FloorResource getFloorResource()
    {
        return new FloorResource();
    }

}
