package com.mall.mallapp.mapper;
import com.mall.mallapp.dto.FloorDTO;
import com.mall.mallapp.model.Floor;
import org.mapstruct.Mapper;
/**
 * Implementation of the {@link FloorMapper} interface.
 * This class maps between {@link Floor} and {@link FloorDTO} classes using the methods defined in the
 * {@link FloorMapper} interface.
 */
@Mapper
public interface FloorMapper {

    /**
     * Maps a Floor entity to a FloorDTO data transfer object.
     *
     * @param floor the Floor entity to map
     * @return the corresponding FloorDTO data transfer object
     */
    FloorDTO ToDto(Floor floor);

    /**
     * Maps a FloorDTO data transfer object to a Floor entity.
     *
     * @param floorDto the FloorDTO data transfer object to map
     * @return the corresponding Floor entity
     */
    Floor ToEntity(FloorDTO floorDto);
}
