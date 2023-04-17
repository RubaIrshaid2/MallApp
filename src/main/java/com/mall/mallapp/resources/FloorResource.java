package com.mall.mallapp.resources;
import com.mall.mallapp.DTO.FloorDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.service.FloorService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/**
 * Resource class for handling HTTP requests related to floors in a mall.
 * This class uses FloorService and FloorRepo for handling the business logic.
 * It supports GET, POST, PUT, and DELETE requests for floors, and
 * forwards requests related to shops to the ShopResource class.
 */
@Path("/")
public class FloorResource {

    private FloorService fs = new FloorService();

    /**
     * Handles HTTP GET requests for retrieving a list of floors in a mall.
     *
     * @param mall_id The ID of the mall to retrieve the floors from.
     * @return A Response object containing a list of FloorDTOs in JSON format, with a status code of 200 on success.
     * Returns a Response object with an error message and a status code of 404 if no floors are found.
     */
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

    /**
     * Handles HTTP POST requests for adding a new floor to a mall.
     *
     * @param mall_id The ID of the mall to add the floor to.
     * @param floor The FloorDTO object containing the details of the new floor to be added.
     * @return A Response object containing the newly added FloorDTO in JSON format, with a status code of 200 on success.
     * Returns a Response object with an error message and a status code of 404 if the operation fails.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFloor(@PathParam("mall_id") int mall_id ,  FloorDTO floor)
    {
        try {
            FloorDTO f = fs.addFloor(mall_id, floor);
            return Response.ok(f.toJSON(), MediaType.APPLICATION_JSON).build();
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
     * Handles HTTP GET requests for retrieving a single floor from a mall.
     *
     * @param mall_id The ID of the mall to retrieve the floor from.
     * @param floor_number The number of the floor to be retrieved.
     * @return A Response object containing the requested FloorDTO in JSON format, with a status code of 200 on success.
     * Returns a Response object with an error message and a status code of 404 if the operation fails.
     */
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
    /**
     *
     * Handles HTTP PUT requests for updating a floor in a mall.
     * Update an existing floor in the mall with the given floor ID.
     * @param mall_id The ID of the mall to which the floor belongs.
     * @param id The ID of the floor to be updated.
     * @param floor The updated FloorDTO object.
     * @return A Response object indicating the success or failure of the update operation.
     * @throws IllegalArgumentException if the given floor is invalid or if there is a problem with the update operation.
     * @HTTP 200 OK - if the floor is updated successfully.
     * @HTTP 404 Not Found - if the floor or the mall is not found.
     * @HTTP 500 Internal Server Error - if there is a problem with the server.
     */
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
    /**
     * Handles HTTP DELETE requests for deleting a floor in a mall.
     *Deletes the floor with the given ID.
     * @param id the ID of the floor to delete.
     * @return a Response object indicating success or failure of the operation.
     */
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
    /**
     * Returns a ShopResource object for accessing the shops associated with the floor with the given ID.
     * @return a ShopResource object for accessing the shops associated with the floor with the given ID.
     */
    @Path("/{floor_id}/shops")
    public ShopResource GetShopeResource()
    {
        return new ShopResource();
    }
}
