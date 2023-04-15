package com.mall.mallapp.service;

import com.mall.mallapp.DTO.FloorDTO;
import com.mall.mallapp.DTO.ShopDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.mapper.ShopMapperImpl;
import com.mall.mallapp.model.Floor;
import com.mall.mallapp.model.Shop;
import com.mall.mallapp.reposotry.ShopRepo;

import java.util.ArrayList;
import java.util.List;

public class ShopService {
    ShopRepo shopRepo = new ShopRepo();

    ShopMapperImpl shopMapper = new ShopMapperImpl();
    public List<ShopDTO> getShops(int mall_id , int floor_id) throws IllegalArgumentException , NotFoundException {

        if(mall_id < 1 || floor_id < 1)
            throw new IllegalArgumentException("mall or floor id is not correct");
        List<Shop> shopList =  shopRepo.getShops(mall_id , floor_id);
        List<ShopDTO> dtoList = new ArrayList<>();

        for(Shop m : shopList)
            dtoList.add(shopMapper.ToDto(m));

        if(shopList.size()==0)
            throw new NotFoundException("Error : no shops");
        return dtoList;
    }

    public ShopDTO getShop(int mall_id , int floor_id , int shop_id) throws IllegalArgumentException,NotFoundException
    {
        if(shop_id < 1 || mall_id < 1 ||floor_id <1  )
            throw new IllegalArgumentException("mall , floor or shop id is not correct");

        ShopDTO sDTO = shopMapper.ToDto(shopRepo.getShop(shop_id));
        if(sDTO == null)
            throw new NotFoundException("Error : the Shop not found");

        return sDTO;
    }


    public ShopDTO add_shop(int mallId , int floorId , ShopDTO shop) throws IllegalArgumentException
    {
        if(mallId < 1 || floorId <1 ||shop.getShop_name().isEmpty() || shop.getShop_name() == null)
            throw new IllegalArgumentException("mall or floor id or data is not correct");
        return shopMapper.ToDto(shopRepo.add_shop(mallId, floorId , shopMapper.ToEntity(shop)));
    }


    public void updateShop(int id , ShopDTO shop) throws IllegalArgumentException
    {
        if(id<1 || shop.getShop_name().isEmpty() || shop.getShop_name()==null)
            throw new IllegalArgumentException("make sure that the data provided is correct");
        shopRepo.updateShop(id , shopMapper.ToEntity(shop));
    }

    public String deleteShop(int id)
    {
        if (id < 1 ) {
            throw new IllegalArgumentException("incorrect id");
        }
        return shopRepo.deleteShop(id);
    }
}
