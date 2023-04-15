package com.mall.mallapp.mapper;

import com.mall.mallapp.DTO.ItemDTO;
import com.mall.mallapp.model.Item;
import org.mapstruct.Mapper;

@Mapper
public interface ItemMapper {

    ItemDTO ToDto(Item item);

    Item ToEntity(ItemDTO itemdto);
}
