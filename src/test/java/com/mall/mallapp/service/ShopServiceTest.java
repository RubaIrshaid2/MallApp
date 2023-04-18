package com.mall.mallapp.service;

import com.mall.mallapp.dto.ShopDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.model.Shop;
import com.mall.mallapp.repository.ShopRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShopServiceTest {

    @InjectMocks
    ShopService ss;
    @Mock
    ShopRepo dataBaseService;
    @BeforeEach
    void setUp() {

        dataBaseService = mock(ShopRepo.class);
        ss = new ShopService(dataBaseService);
    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void When_shopsAreExist_Expect_returningTheShops() throws NotFoundException {
        List<Shop> list = new ArrayList<>();
        list.add(new Shop(1,1,1,"nike","desc","9-9"));
        list.add(new Shop(2,1,1,"addidas","desc","9-10" ));

        when(dataBaseService.getShops(1,1)).thenReturn(list);

        List<ShopDTO> actualList = ss.getShops(1,1);

        List<ShopDTO> Expectedlist = new ArrayList<>();
        Expectedlist.add(new ShopDTO(1,1,"nike","desc","9-9"));
        Expectedlist.add(new ShopDTO(1,1,"addidas","desc","9-10" ));

        assertEquals(Expectedlist , actualList);

        verify(dataBaseService).getShops(1,1);

    }
    @Test
    void when_noShops_expect_throwNotFoundException()
    {
        List<Shop> list = new ArrayList<>();

        when(dataBaseService.getShops(1,1)).thenReturn(list);


        assertThrows(NotFoundException.class , ()->ss.getShops(1,1));

        verify(dataBaseService).getShops(1,1);
    }

    @Test
    void when_2ShopssExist_expect_sizeEqualTo2() throws NotFoundException {
        List<Shop> list = new ArrayList<>();
        list.add(new Shop(1,1,1,"nike","desc","9-9"));
        list.add(new Shop(2,1,1,"addidas","desc","9-10" ));

        when(dataBaseService.getShops(1,1)).thenReturn(list);

        List<ShopDTO> actualList = ss.getShops(1,1);

        assertEquals(2 , actualList.size());

        verify(dataBaseService).getShops(1,1);
    }

    @Test
    void when_ShopsReturned_expect_beingShopDTO() throws NotFoundException {
        List<Shop> list = new ArrayList<>();
        list.add(new Shop(1,1,1,"nike","desc","9-9"));
        list.add(new Shop(2,1,1,"addidas","desc","9-10" ));

        when(dataBaseService.getShops(1,1)).thenReturn(list);

        List<ShopDTO> actualList = ss.getShops(1,1);

        boolean sameKind = true;
        Class<?> firstClass = actualList.get(0).getClass();
        for (Object obj : actualList) {
            if (obj.getClass() != firstClass) {
                sameKind = false;
                break;
            }
        }

        assertTrue(sameKind);
    }


    @Test
    void When_shopIdIsCorrect_expect_returningTheShop() throws NotFoundException {
        Shop m = new Shop(2,1,1,"addidas","desc","9-10" );
        when(dataBaseService.getShop(1)).thenReturn(m);

        ShopDTO Expected = new ShopDTO(1,1,"addidas","desc","9-10" );
        ShopDTO actual = ss.getShop(1,1,1);

        assertEquals(Expected,actual);
    }

    @Test
    void when_getShopIsCalled_expect_returningDTOShop() throws NotFoundException {
        Shop s = new Shop(2,1,1,"addidas","desc","9-10" );
        when(dataBaseService.getShop(1)).thenReturn(s);

        assertInstanceOf(ShopDTO.class , ss.getShop(1,1,1));
    }

    @Test
    void when_gettingShopWithWrongId_expect_IllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class , ()->ss.getShop(-1,1,1));
    }

    @Test
    @Disabled
    void When_addingShop_expect_retrieveTheSameShop()
    {
        Shop s = new Shop(2,1,1,"addidas","desc","9-10" );
        when(dataBaseService.addShop(1,1,s)).thenReturn(s);

        ShopDTO expected = new ShopDTO(1,1,"addidas","desc","9-10" );
        ShopDTO actual = ss.addShop(1,1,expected);

        assertEquals(expected,actual);
    }

    @Test
    void when_addingShopWithEmptyFields_expect_IllegalArgumentException()
    {
        ShopDTO sDTO = new ShopDTO(1,1,"","desc","9-10" );
        assertThrows(IllegalArgumentException.class , ()->ss.addShop(1,1,sDTO));
    }

    @Test
    void when_updateShopWithIncorrectId_expect_throwIllegalArgumentException()
    {
        ShopDTO sDTO = new ShopDTO(-11,1,"","desc","9-10" );
        assertThrows(IllegalArgumentException.class ,()->ss.updateShop(-1,sDTO));
    }
    @Test
    void when_updateShopWithIncorrectData_expect_throwIllegalArgumentException()
    {
        ShopDTO sDTO = new ShopDTO(1,1,"","desc","9-10" );
        assertThrows(IllegalArgumentException.class ,()->ss.updateShop(1,sDTO));
    }

    @Test
    void When_DeleteShop_expect_returningDeletedsuccessfully()
    {
        when(dataBaseService.deleteShop(1)).thenReturn("Deleted successfully");

        assertEquals("Deleted successfully",ss.deleteShop(1));
    }
    @Test
    void deleteshop() {

        assertThrows(IllegalArgumentException.class , ()->ss.deleteShop(-1));

        //test
//
//        Mall mall = ms.getMall(2);
//        ms.deleteMall(2);
//        mall=ms.getMall(2);
//        assertNull(mall);
    }

}