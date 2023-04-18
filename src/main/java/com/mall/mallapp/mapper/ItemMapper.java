package com.mall.mallapp.mapper;
import com.mall.mallapp.dto.ItemDTO;
import com.mall.mallapp.model.Item;
import org.mapstruct.Mapper;

/**
 * The ItemMapperImpl class is responsible for mapping between Item and ItemDTO objects.
 * It implements the ItemMapper interface.
 */
@Mapper
public interface ItemMapper {

    /**
     * Maps an Item object to an ItemDTO object.
     *
     * @param item the Item object to map
     * @return an ItemDTO object representing the mapped item
     */
    ItemDTO ToDto(Item item);

    /**
     * Maps an ItemDTO object to an Item object.
     *
     * @param itemdto the ItemDTO object to map
     * @return an Item object representing the mapped itemdto
     */
    Item ToEntity(ItemDTO itemdto);
}
