package com.mall.mallapp.service;
import com.mall.mallapp.DTO.FloorDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.exception.ObjectExistsException;
import com.mall.mallapp.mapper.FloorMapperImpl;
import com.mall.mallapp.model.Floor;
import com.mall.mallapp.reposotry.FloorRepo;
import java.util.ArrayList;
import java.util.List;
/**
 *The FloorService class provides methods to interact with the floor data in the database.
 */
public class FloorService {

    private FloorRepo floorRepo = new FloorRepo();
    private FloorMapperImpl floorMapper = new FloorMapperImpl();

    /**
     * Default constructor for FloorService.
     */
    public FloorService(){};

    /**
     * Constructor for FloorService that takes in a FloorRepo object.
     *
     * @param fr FloorRepo object
     */
    public FloorService(FloorRepo fr){
        floorRepo = fr ;
    };

    /**
     * Retrieves a list of all floors for the specified mall.
     *
     * @param mall_id the id of the mall to retrieve floors for
     * @return a list of FloorDTO objects for the mall
     * @throws NotFoundException if no floors are found for the mall
     */
    public List<FloorDTO> getFloors(int mall_id) throws NotFoundException
    {
        List<FloorDTO> dtoList = new ArrayList<>();
        List<Floor> floorList = floorRepo.getFloors(mall_id);

        for(Floor m : floorList)
            dtoList.add(floorMapper.ToDto(m));

        if(floorList.size() == 0)
            throw new NotFoundException("Error : No Floors");
        return dtoList;
    }

    /**
     * Retrieves a single floor for the specified mall and floor number.
     *
     * @param mall_id the id of the mall to retrieve the floor for
     * @param floor_number the floor number to retrieve
     * @return the FloorDTO object for the specified mall and floor number
     * @throws IllegalArgumentException if either the mall_id or floor_number is less than 1
     * @throws NotFoundException if the floor is not found
     */
    public FloorDTO getFloor(int mall_id , int floor_number) throws IllegalArgumentException,NotFoundException
    {
        if(mall_id < 1 || floor_number < 1)
            throw new IllegalArgumentException("mall id or floor number is not correct");
        FloorDTO floor = floorMapper.ToDto(floorRepo.getFloor(mall_id, floor_number));
        if(floor == null)
            throw new NotFoundException("Error : the floor not found");
        return floor;
    }

    /**
     * Adds a floor to the specified mall.
     *
     * @param Mall_id the id of the mall to add the floor to
     * @param floor the FloorDTO object to add
     * @return the FloorDTO object that was added
     * @throws IllegalArgumentException if the mall_id or data is not correct
     */
    public FloorDTO addFloor(int Mall_id , FloorDTO floor) throws IllegalArgumentException , ObjectExistsException
    {
        if(Mall_id <1 || floor.getFloorNumber()<1 || floor.getCategory().isEmpty())
            throw new IllegalArgumentException("Error : mall id or data is not correct");
        try {
            return floorMapper.ToDto(floorRepo.add_Floor(Mall_id, floorMapper.ToEntity(floor)));
        }
        catch (ObjectExistsException oe)
        {
            throw new ObjectExistsException("the floor is already exist");
        }

    }

    /**
     *
     * Update a floor with given mall_id and id with new information.
     * @param mall_id The ID of the mall where the floor belongs.
     * @param id The ID of the floor to be updated.
     * @param floor The new information of the floor.
     * @throws IllegalArgumentException if the mall_id, id or data in the FloorDTO object are not valid.
     */
    public void updateFloor(int mall_id , int id , FloorDTO floor) throws IllegalArgumentException
    {
        if(id <1 || floor.getFloorNumber()<1 || floor.getCategory().isEmpty())
            throw new IllegalArgumentException("Error : mall id or data is not correct");
        floorRepo.updateFloor(mall_id , id,floorMapper.ToEntity(floor));
    }

    /**
     *
     * Delete a floor with the given id.
     * @param id The ID of the floor to be deleted.
     * @return A message indicating if the deletion was successful.
     * @throws IllegalArgumentException if the ID is not valid.
     */
    public String deleteFloor(int id)throws IllegalArgumentException
    {
        if (id < 1 ) {
            throw new IllegalArgumentException("incorrect id");
        }
        return floorRepo.deleteFloor(id);
    }
}
