package com.mall.mallapp.service;
import com.mall.mallapp.DTO.ItemDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.mapper.ItemMapperImpl;
import com.mall.mallapp.model.Item;
import com.mall.mallapp.reposotry.ItemRepo;
import java.util.ArrayList;
import java.util.List;
/**
 * This class contains methods for handling Item related operations such as getting, adding, updating, and deleting Items.
 */
public class ItemService {

    private ItemRepo itemRepo = new ItemRepo();
    private ItemMapperImpl itemMapper = new ItemMapperImpl();

    /**
     * Default constructor
     */
    public ItemService(){}

    /**
     * Constructor that takes an ItemRepo object as input
     * @param ir An ItemRepo object
     */
    public ItemService(ItemRepo ir)
    {
        itemRepo = ir ;
    }

    /**
     * Returns a list of ItemDTO objects based on the given shop ID
     * @param shop_id The ID of the shop
     * @return A list of ItemDTO objects
     * @throws NotFoundException If no Items are found for the given shop ID
     */

    public List<ItemDTO> getItems(int shop_id) throws NotFoundException {

        List<Item> itemList = itemRepo.getItems(shop_id);
        List<ItemDTO> DTOList = new ArrayList<>();

        for(Item i : itemList)
            DTOList.add(itemMapper.ToDto(i));

        if(itemList.size()==0)
            throw new NotFoundException("Error : No Items in this shop");
        return DTOList;
    }

    /**
     * Returns an ItemDTO object based on the given item ID
     * @param item_id The ID of the item
     * @return An ItemDTO object
     * @throws IllegalArgumentException If the item ID is less than 1
     * @throws NotFoundException If the Item with the given ID is not found
     */
    public ItemDTO getItem(int item_id) throws IllegalArgumentException,NotFoundException
    {
        if(item_id < 1 )
            throw new IllegalArgumentException("item id is not correct");
        ItemDTO iDto =  itemMapper.ToDto(itemRepo.getItem(item_id));
        if(iDto == null)
            throw new NotFoundException("Error : the Item not found");
        return iDto;
    }

    /**
     * Adds a new Item to the specified shop
     * @param shop_id The ID of the shop where the Item is to be added
     * @param item An ItemDTO object containing the data for the new Item
     * @return An ItemDTO object representing the newly added Item
     * @throws IllegalArgumentException If the shop ID is less than 1 or if the name of the new Item is empty or null
     */
    public ItemDTO addItem(int shop_id , ItemDTO item) throws IllegalArgumentException
    {
        if(shop_id < 1 || item.getName().isEmpty() || item.getName()==null)
            throw new IllegalArgumentException("shop id or data of the new item is not correct");
        return itemMapper.ToDto(itemRepo.addItem(shop_id, itemMapper.ToEntity(item)));
    }

    /**
     * Updates an existing Item in the specified shop
     * @param id The ID of the Item to be updated
     * @param shop_id The ID of the shop where the Item to be updated is located
     * @param item An ItemDTO object containing the updated data for the Item
     * @throws IllegalArgumentException If the shop or item ID is less than 1 or if some data is missing
     */
    public void updateItem(int id ,int shop_id , ItemDTO item)
    {
        if(id < 1 || shop_id< 1 || item.getName().isEmpty())
            throw new IllegalArgumentException("shop or item id or some data is not correct");
        itemRepo.updateItem(id, shop_id , itemMapper.ToEntity(item));
    }

    /**
     *
     * Deletes an item from the database.
     * @param id the id of the item to be deleted
     * @return a message indicating whether the operation was successful or not
     * @throws IllegalArgumentException if the id is less than 1
     */
    public String deleteItem(int id)
    {
        if (id < 1 ) {
            throw new IllegalArgumentException("incorrect id");
        }
        return itemRepo.deleteItem(id);
    }
}
