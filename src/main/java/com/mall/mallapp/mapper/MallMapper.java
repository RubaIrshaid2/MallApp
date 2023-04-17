package com.mall.mallapp.mapper;
import com.mall.mallapp.DTO.MallDTO;
import com.mall.mallapp.model.Mall;
import org.mapstruct.Mapper;

@Mapper
public interface MallMapper {


    MallDTO ToDto(Mall mall);


    Mall toEntity(MallDTO malldto);
}
