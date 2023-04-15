package com.mall.mallapp.mapper;
//import org.mapstruct.Mapper;

import com.mall.mallapp.DTO.MallDTO;
import com.mall.mallapp.model.Mall;
import org.mapstruct.Mapper;

@Mapper
public interface MallMapper {

    //map from mall to mallDto

    MallDTO ToDto(Mall mall);

    //map from mallDto to mall

    Mall toEntity(MallDTO malldto);
}
