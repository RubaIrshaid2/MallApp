package com.mall.mallapp.service;

import com.mall.mallapp.DTO.FloorDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.mapper.FloorMapperImpl;
import com.mall.mallapp.model.Floor;
import com.mall.mallapp.model.Mall;
import com.mall.mallapp.reposotry.FloorRepo;

import java.util.ArrayList;
import java.util.List;

public class FloorService {

    FloorRepo floorRepo = new FloorRepo();
    FloorMapperImpl floorMapper = new FloorMapperImpl();
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

    public FloorDTO getFloor(int mall_id , int floor_number) throws IllegalArgumentException,NotFoundException
    {
        if(mall_id < 1 || floor_number < 1)
            throw new IllegalArgumentException("mall id or floor number is not correct");
        FloorDTO floor = floorMapper.ToDto(floorRepo.getFloor(mall_id, floor_number));
        if(floor == null)
            throw new NotFoundException("Error : the floor not found");
        return floor;
    }


    public FloorDTO add_Floor(int Mall_id , FloorDTO floor) throws IllegalArgumentException
    {
        if(Mall_id <1 || floor.getFloor_number()<1 || floor.getCategory().isEmpty())
            throw new IllegalArgumentException("Error : mall id or data is not correct");
        return floorMapper.ToDto(floorRepo.add_Floor(Mall_id , floorMapper.ToEntity(floor)));
    }

    public void updateFloor(int mall_id , int id , FloorDTO floor) throws IllegalArgumentException
    {
        if(id <1 || floor.getFloor_number()<1 || floor.getCategory().isEmpty())
            throw new IllegalArgumentException("Error : mall id or data is not correct");
        floorRepo.updateFloor(mall_id , id,floorMapper.ToEntity(floor));
    }

    public String deleteFloor(int id)throws IllegalArgumentException
    {
        if (id < 1 ) {
            throw new IllegalArgumentException("incorrect id");
        }
        return floorRepo.deleteFloor(id);
    }
}
