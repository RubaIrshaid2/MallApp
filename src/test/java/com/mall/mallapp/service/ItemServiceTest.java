package com.mall.mallapp.service;

import com.mall.mallapp.DTO.ItemDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.model.Item;
import com.mall.mallapp.reposotry.ItemRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class ItemServiceTest {

    @InjectMocks
    ItemService is;
    @Mock
    ItemRepo dataBaseService;
    @BeforeEach
    void setUp() {

        dataBaseService = mock(ItemRepo.class);
        is = new ItemService(dataBaseService);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void when_noItems_expect_throwNotFoundException()
    {
        List<Item> list = new ArrayList<>();

        when(dataBaseService.getItems(1)).thenReturn(list);


        assertThrows(NotFoundException.class , ()-> is.getItems(1));

        verify(dataBaseService).getItems(1);
    }


    @Test
    void when_2itemsExist_expect_sizeEqualTo2()
    {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1,1,"item 1 ",22,"desc",12));
        list.add(new Item(2,1,"item 2 ",22,"desc",12));

        when(dataBaseService.getItems(1)).thenReturn(list);

        List<ItemDTO> actualList = is.getItems(1);

        assertEquals(2 , actualList.size());

        verify(dataBaseService).getItems(1);
    }

    @Test
    void when_itemsReturned_expect_beingItemDTO()
    {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1,1,"item 1 ",22,"desc",12));
        list.add(new Item(2,1,"item 2 ",22,"desc",12));

        when(dataBaseService.getItems(1)).thenReturn(list);

        List<ItemDTO> actualList = is.getItems(1);

        boolean sameKind = true;
        Class<?> firstClass = actualList.get(0).getClass();
        for (Object obj : actualList) {
            if (obj.getClass() != firstClass) {
                sameKind = false;
                break;
            }
        }


        assertInstanceOf(ItemDTO.class , actualList.get(0));
        assertTrue(sameKind);
    }


    @Test
    void when_getItemIsCalled_expect_returningDTOItem()
    {
        Item i = new Item(1,1,"item 1 ",22,"desc",12);
        when(dataBaseService.getItem(1)).thenReturn(i);

        assertInstanceOf(ItemDTO.class , is.getItem(1));
    }

    @Test
    void when_gettingItemWithWrongId_expect_IllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class , ()-> is.getItem(-1));
    }


    @Test
    void when_addingItemWithEmptyFields_expect_IllegalArgumentException()
    {
        ItemDTO iDto = new ItemDTO("",22,"desc",12);
        assertThrows(IllegalArgumentException.class , ()-> is.addItem(1,iDto));
    }

    @Test
    void when_updateItemWithIncorrectId_expect_throwIllegalArgumentException()
    {
        ItemDTO iDto = new ItemDTO("item",22,"desc",12);
        assertThrows(IllegalArgumentException.class ,()-> is.updateItem(-1,1,iDto));
    }
    @Test
    void when_updateItemWithIncorrectData_expect_throwIllegalArgumentException()
    {
        ItemDTO iDto = new ItemDTO("",22,"desc",12);
        assertThrows(IllegalArgumentException.class ,()-> is.updateItem(1,1,iDto));
    }
//
    @Test
    void When_DeleteItem_expect_returningDeletedsuccessfully()
    {
        when(dataBaseService.deleteItem(1)).thenReturn("Deleted successfully");

        assertEquals("Deleted successfully", is.deleteItem(1));
    }
    @Test
    void deleteItem() {

        assertThrows(IllegalArgumentException.class , ()-> is.deleteItem(-1));

    }
}