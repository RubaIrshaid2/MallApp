package com.mall.mallapp.mapper;

import com.mall.mallapp.DTO.FloorDTO;
import com.mall.mallapp.model.Floor;
import org.mapstruct.Mapper;

@Mapper
public interface FloorMapper {

    //mapping from floor to floorDTO
    FloorDTO ToDto(Floor floor);

    //mapping form FloorDTO to Floor entity
    Floor ToEntity(FloorDTO floorDto);
}
