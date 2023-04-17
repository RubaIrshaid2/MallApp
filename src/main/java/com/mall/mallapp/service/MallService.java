package com.mall.mallapp.service;
import com.mall.mallapp.DTO.MallDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.exception.ObjectExistsException;
import com.mall.mallapp.mapper.MallMapperImpl;
import com.mall.mallapp.model.Mall;
import com.mall.mallapp.reposotry.MallRepo;
import java.util.ArrayList;
import java.util.List;
/**
 * The MallService class provides methods for retrieving, adding, updating, and deleting malls.
 */
public class MallService {

    /**
     * The mall repository instance used to interact with the mall database.
     */
    private MallRepo mallRepo = new MallRepo();
    /**
     * The mall mapper instance used to convert between Mall and MallDTO objects.
     */
    private MallMapperImpl mallMapper = new MallMapperImpl();


    /**
     * Constructs a new MallService object with the specified MallRepo instance.
     * @param mr the MallRepo instance to use
     */
    public MallService(MallRepo mr) {
        mallRepo = mr ;
    }

    /**
     * Constructs a new MallService object.
     */
    public MallService() {
    }

    /**
     * Retrieves all malls and returns them as a list of MallDTO objects.
     * @return a list of MallDTO objects representing all malls
     * @throws NotFoundException if no malls are found
     */
    public List<MallDTO> getAllMalls() throws NotFoundException
    {
        List<Mall> mall = mallRepo.GetMalls();
        List<MallDTO> dtoList = new ArrayList<MallDTO>();

        for(Mall m : mall)
            dtoList.add(mallMapper.ToDto(m));

        if(mall.size()==0)
            throw new NotFoundException("error : malls not found");
        return dtoList;
    }

    /**
     * Retrieves the mall with the specified id and returns it as a MallDTO object.
     * @param id the id of the mall to retrieve
     * @return a MallDTO object representing the mall with the specified id
     * @throws NotFoundException if no mall with the specified id is found
     */
    public MallDTO getMall(int id) throws NotFoundException
    {
        MallDTO mall = mallMapper.ToDto(mallRepo.GetMall(id));

        if(mall == null)
            throw new NotFoundException("error : mall not found");
        return mall;
    }

    /**
     * Adds a new mall to the database and returns it as a MallDTO object.
     * @param mall the MallDTO object representing the mall to add
     * @return a MallDTO object representing the added mall
     * @throws IllegalArgumentException if the data of the mall is not correct
     * @throws ObjectExistsException if the mall is already exist by checking the name
     */
    public MallDTO addMall(MallDTO mall) throws IllegalArgumentException, ObjectExistsException
    {
        if (mall.getName().isEmpty() || mall.getAddress().isEmpty() || mall.getName() == null || mall.getAddress() == null) {
            throw new IllegalArgumentException("the data of the mall is not correct");
        }
        try
        {
            return mallMapper.ToDto(mallRepo.AddMAll(mallMapper.toEntity(mall)));
        }
        catch (ObjectExistsException oe)
        {
            throw new ObjectExistsException("the mall is already exist");
        }

    }

    /**
     * Updates the mall with the specified id with the provided data.
     * @param id the id of the mall to update
     * @param mall the MallDTO object representing the updated mall data
     * @throws IllegalArgumentException if the id or data of the mall is not correct
     */
    public void updateMall(int id , MallDTO mall) throws IllegalArgumentException
    {
        if (id < 1 || mall.getName().isEmpty() || mall.getAddress().isEmpty() || mall.getName() == null || mall.getAddress() == null) {
            throw new IllegalArgumentException("incorrect id or data");
        }
        mallRepo.UpdateMall(id,mallMapper.toEntity(mall));
    }

    /**
     * Deletes the mall with the specified id from the database.
     * @param id the id of the mall to delete
     * @return a message indicating the status of the delete operation
     * @throws IllegalArgumentException if the id is not correct
     * */
    public String deleteMall(int id) throws IllegalArgumentException
    {
        if (id < 1 ) {
            throw new IllegalArgumentException("incorrect id");
        }
        return mallRepo.DeleteMall(id);
    }
}
