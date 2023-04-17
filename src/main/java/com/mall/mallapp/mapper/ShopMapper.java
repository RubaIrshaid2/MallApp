package com.mall.mallapp.mapper;
import com.mall.mallapp.DTO.ShopDTO;
import com.mall.mallapp.model.Shop;
import org.mapstruct.Mapper;

@Mapper
public interface ShopMapper {

    ShopDTO ToDto(Shop shop);

    Shop ToEntity(ShopDTO shopDTO);
}

