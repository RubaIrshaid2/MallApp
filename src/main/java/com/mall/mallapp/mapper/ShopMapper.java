package com.mall.mallapp.mapper;
import com.mall.mallapp.dto.ShopDTO;
import com.mall.mallapp.model.Shop;
import org.mapstruct.Mapper;

/**
 *This class provides methods for mapping Shop objects to ShopDTO objects and vice versa.
 *It implements the ShopMapper interface.
 */
@Mapper
public interface ShopMapper {

    /**
     * Maps a Shop object to a ShopDTO object.
     *
     * @param shop the Shop object to be mapped
     * @return the mapped ShopDTO object
     */
    ShopDTO ToDto(Shop shop);

    /**
     * Maps a ShopDTO object to a Shop object.
     * @param shopDTO the ShopDTO object to be mapped
     * @return the mapped Shop object
     */
    Shop ToEntity(ShopDTO shopDTO);
}

