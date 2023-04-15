package com.mall.mallapp.service;

import com.mall.mallapp.DTO.ItemDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.mapper.ItemMapperImpl;
import com.mall.mallapp.model.Item;
import com.mall.mallapp.reposotry.ItemRepo;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    ItemRepo itemRepo = new ItemRepo();
    ItemMapperImpl itemMapper = new ItemMapperImpl();

    public List<ItemDTO> getItems(int shop_id) throws NotFoundException {

        List<Item> itemList = itemRepo.getItems(shop_id);
        List<ItemDTO> DTOList = new ArrayList<>();

        for(Item i : itemList)
            DTOList.add(itemMapper.ToDto(i));

        if(itemList.size()==0)
            throw new NotFoundException("Error : No Items in this shop");
        return DTOList;
    }

    public ItemDTO getItem(int item_id) throws IllegalArgumentException,NotFoundException
    {
        if(item_id < 1 )
            throw new IllegalArgumentException("item id is not correct");
        ItemDTO iDto =  itemMapper.ToDto(itemRepo.getItem(item_id));
        if(iDto == null)
            throw new NotFoundException("Error : the Item not found");
        return iDto;
    }
    public ItemDTO add_item(int shop_id ,ItemDTO item) throws IllegalArgumentException
    {
        if(shop_id < 1 || item.getName().isEmpty() || item.getName()==null)
            throw new IllegalArgumentException("shop id or data of the new item is not correct");
        return itemMapper.ToDto(itemRepo.add_item(shop_id, itemMapper.ToEntity(item)));
    }

    public void updateItem(int id ,int shop_id , ItemDTO item)
    {
        if(id < 1 || shop_id< 1 || item.getName().isEmpty())
            throw new IllegalArgumentException("shop or item id or some data is not correct");
        itemRepo.updateItem(id, shop_id , itemMapper.ToEntity(item));
    }

    public String deleteItem(int id)
    {
        if (id < 1 ) {
            throw new IllegalArgumentException("incorrect id");
        }
        return itemRepo.deleteItem(id);
    }
}
