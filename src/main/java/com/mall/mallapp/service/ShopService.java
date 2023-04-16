/**
 * This class represents the ShopService, which provides functionality for managing shops in a mall app.
 * It uses ShopRepo and ShopMapperImpl to interact with the data layer.
 */
package com.mall.mallapp.service;

import com.mall.mallapp.DTO.ShopDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.mapper.ShopMapperImpl;
import com.mall.mallapp.model.Shop;
import com.mall.mallapp.reposotry.ShopRepo;

import java.util.ArrayList;
import java.util.List;

public class ShopService {
    // instance of ShopRepo for accessing data layer
    ShopRepo shopRepo = new ShopRepo();

    // instance of ShopMapperImpl for mapping between DTOs and entities
    ShopMapperImpl shopMapper = new ShopMapperImpl();

    /**
     * Default constructor for ShopService.
     */
    public ShopService(){};

    /**
     * Constructor for ShopService.
     * @param sr instance of ShopRepo
     */
    public ShopService(ShopRepo sr)
    {
        shopRepo = sr ;
    }

    /**
     * Gets a list of all the shops in a mall and floor.
     * @param mall_id ID of the mall
     * @param floor_id ID of the floor
     * @return list of ShopDTOs representing the shops
     * @throws IllegalArgumentException if mall or floor ID is less than 1
     * @throws NotFoundException if no shops are found for the given mall and floor
     */
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

    /**
     * Gets a list of all the shops in a mall and floor.
     * @param mall_id ID of the mall
     * @param floor_id ID of the floor
     * @return list of ShopDTOs representing the shops
     * @throws IllegalArgumentException if mall or floor ID is less than 1
     * @throws NotFoundException if no shops are found for the given mall and floor
     */
    public ShopDTO getShop(int mall_id , int floor_id , int shop_id) throws IllegalArgumentException,NotFoundException
    {
        if(shop_id < 1 || mall_id < 1 ||floor_id <1  )
            throw new IllegalArgumentException("mall , floor or shop id is not correct");

        ShopDTO sDTO = shopMapper.ToDto(shopRepo.getShop(shop_id));
        if(sDTO == null)
            throw new NotFoundException("Error : the Shop not found");

        return sDTO;
    }


    /**
     * Adds a shop to a mall and floor.
     * @param mallId ID of the mall
     * @param floorId ID of the floor
     * @param shop ShopDTO representing the shop to be added
     * @return ShopDTO representing the added shop
     * @throws IllegalArgumentException if mall or floor ID is less than 1 or shop data is missing or incorrect
     */
    public ShopDTO add_shop(int mallId , int floorId , ShopDTO shop) throws IllegalArgumentException
    {
        if(mallId < 1 || floorId <1 ||shop.getShop_name().isEmpty() || shop.getShop_name() == null)
            throw new IllegalArgumentException("mall or floor id or data is not correct");
        return shopMapper.ToDto(shopRepo.add_shop(mallId, floorId , shopMapper.ToEntity(shop)));
    }


    /**
     * -Updates a Shop object with the given ID using the provided ShopDTO object.
     * @param id the ID of the Shop object to be updated.
     * @param shop the ShopDTO object containing the updated data.
     * @throws IllegalArgumentException if the provided ID is less than 1, or if the ShopDTO object's shop_name attribute is null or empty.
     */
    public void updateShop(int id , ShopDTO shop) throws IllegalArgumentException
    {
        if(id<1 || shop.getShop_name().isEmpty() || shop.getShop_name()==null)
            throw new IllegalArgumentException("make sure that the data provided is correct");
        shopRepo.updateShop(id , shopMapper.ToEntity(shop));
    }

    /**
     * Deletes the Shop object with the given ID.
     * @param id the ID of the Shop object to be deleted.
     * @return a string indicating whether the deletion was successful or not.
     * @throws IllegalArgumentException if the provided ID is less than 1.
     */
    public String deleteShop(int id)
    {
        if (id < 1 ) {
            throw new IllegalArgumentException("incorrect id");
        }
        return shopRepo.deleteShop(id);
    }
}
