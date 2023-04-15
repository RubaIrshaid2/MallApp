package com.mall.mallapp.service;


import com.mall.mallapp.DTO.MallDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.mapper.MallMapperImpl;
import com.mall.mallapp.model.Mall;
import com.mall.mallapp.reposotry.MallRepo;

import java.util.ArrayList;
import java.util.List;


public class MallService {

    MallRepo mallRepo = new MallRepo();
    private MallMapperImpl mallMapper = new MallMapperImpl();


    public MallService() {
    }

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

    public MallDTO getMall(int id) throws NotFoundException
    {
        MallDTO mall = mallMapper.ToDto(mallRepo.GetMall(id));

        if(mall == null)
            throw new NotFoundException("error : mall not found");
        return mall;
    }

    public MallDTO add_Mall(MallDTO mall) throws IllegalArgumentException
    {
        if (mall.getName().isEmpty() || mall.getAddress().isEmpty() || mall.getName() == null || mall.getAddress() == null) {
            throw new IllegalArgumentException("the data of the mall is not correct");
        }
        return mallMapper.ToDto(mallRepo.AddMAll(mallMapper.toEntity(mall)));
    }

    public void updateMall(int id , MallDTO mall) throws IllegalArgumentException
    {
        if (id < 1 || mall.getName().isEmpty() || mall.getAddress().isEmpty() || mall.getName() == null || mall.getAddress() == null) {
            throw new IllegalArgumentException("incorrect id or data");
        }
        mallRepo.UpdateMall(id,mallMapper.toEntity(mall));
    }

    public String deleteMall(int id) throws IllegalArgumentException
    {
        if (id < 1 ) {
            throw new IllegalArgumentException("incorrect id");
        }
        return mallRepo.DeleteMall(id);
    }
}
