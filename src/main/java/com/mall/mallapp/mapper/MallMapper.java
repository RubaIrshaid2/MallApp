package com.mall.mallapp.mapper;
import com.mall.mallapp.dto.MallDTO;
import com.mall.mallapp.model.Mall;
import org.mapstruct.Mapper;

/**
 * The MallMapperImpl class is responsible for mapping between Mall and MallDTO objects.
 *  It implements the MallMapper interface.
 */
@Mapper
public interface MallMapper {

    /**
     * Maps a Mall object to a MallDTO object.
     *
     * @param mall the Mall object to map
     * @return a MallDTO object representing the mapped mall
     */

    MallDTO ToDto(Mall mall);

    /**
     * Maps a MallDTO object to a Mall object.
     *
     * @param malldto the MallDTO object to map
     * @return a Mall object representing the mapped malldto
     */
    Mall toEntity(MallDTO malldto);
}
