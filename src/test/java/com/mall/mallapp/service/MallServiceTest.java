package com.mall.mallapp.service;

import com.mall.mallapp.DTO.MallDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.model.Mall;
import com.mall.mallapp.reposotry.MallRepo;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MallServiceTest {

    @InjectMocks
    MallService ms;
    @Mock
    MallRepo dataBaseService;
    @BeforeEach
    void setUp() {

        dataBaseService = mock(MallRepo.class);
        ms = new MallService(dataBaseService);
    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void When_mallsAreExist_Expect_returningTheMalls()
    {
        List<Mall> list = new ArrayList<>();
        list.add(new Mall(1,"City center Mall" ,"Hebron", 13 , "in the center of the city" ));
        list.add(new Mall(2,"Ramallah Mall" , "Ramallah" ,30 ,"ramallah desc" ));

        when(dataBaseService.GetMalls()).thenReturn(list);

        List<MallDTO> actualList = ms.getAllMalls();

        List<MallDTO> Expectedlist = new ArrayList<>();
        Expectedlist.add(new MallDTO("City center Mall" ,"Hebron", 13 , "in the center of the city" ));
        Expectedlist.add(new MallDTO("Ramallah Mall" , "Ramallah" ,30 ,"ramallah desc" ));

        assertEquals(Expectedlist , actualList);

        verify(dataBaseService).GetMalls();

    }
    @Test
    void when_noMalls_expect_throwNotFoundException()
    {
        List<Mall> list = new ArrayList<>();

        when(dataBaseService.GetMalls()).thenReturn(list);


        assertThrows(NotFoundException.class , ()->ms.getAllMalls());

       verify(dataBaseService).GetMalls();
    }

    @Test
    void when_2mallsExist_expect_sizeEqualTo2()
    {
        List<Mall> list = new ArrayList<>();
        list.add(new Mall(1,"City center Mall" ,"Hebron", 13 , "in the center of the city" ));
        list.add(new Mall(2,"Ramallah Mall" , "Ramallah" ,30 ,"ramallah desc" ));

        when(dataBaseService.GetMalls()).thenReturn(list);

        List<MallDTO> actualList = ms.getAllMalls();

        assertEquals(2 , actualList.size());

        verify(dataBaseService).GetMalls();
    }

    @Test
    void when_mallsReturned_expect_beingMallDTO()
    {
        List<Mall> list = new ArrayList<>();
        list.add(new Mall(1,"City center Mall" ,"Hebron", 13 , "in the center of the city" ));
        list.add(new Mall(2,"Ramallah Mall" , "Ramallah" ,30 ,"ramallah desc" ));

        when(dataBaseService.GetMalls()).thenReturn(list);

        List<MallDTO> actualList = ms.getAllMalls();

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
    void When_mallIdIsCorrect_expect_returningTheMall()
    {
        Mall m = new Mall(1,"lacasa Mall","Ramallah",10,"lacasa desc");
        when(dataBaseService.GetMall(1)).thenReturn(m);

        MallDTO Expected = new MallDTO("lacasa Mall","Ramallah",10,"lacasa desc");
        MallDTO actual = ms.getMall(1);

        assertEquals(Expected,actual);
    }

    @Test
    void when_getMallIsCalled_expect_returningDTOMall()
    {
        Mall m = new Mall(1,"lacasa Mall","Ramallah",10,"lacasa desc");
        when(dataBaseService.GetMall(1)).thenReturn(m);

        assertInstanceOf(MallDTO.class , ms.getMall(1));
    }

    @Test
    void when_gettingMallWithWrongId_expect_NotFoundException()
    {
        when(dataBaseService.GetMall(30)).thenReturn(null);
        when(dataBaseService.GetMall(-1)).thenReturn(null);
        assertAll(
                ()->assertThrows(NotFoundException.class ,()->ms.getMall(30)),
                ()->assertThrows(NotFoundException.class ,()->ms.getMall(-1))
        );
    }
    @Test
    void When_addingMall_expect_retrieveTheSameMall()
    {
        Mall newMall = new Mall(15 , "new Mall" , "address" , 123 , "desc");
        when(dataBaseService.AddMAll(newMall)).thenReturn(newMall);

        MallDTO expected = new MallDTO( "new Mall" , "address" , 123 , "desc");
        MallDTO actual = ms.addMall(expected);

        assertEquals(expected,actual);
    }

    @Test
    void when_addingMallWithEmptyFields_expect_IllegalArgumentException()
    {
        MallDTO mallDTO = new MallDTO( "" , "address" , 123 , "desc");
        assertThrows(IllegalArgumentException.class , ()->ms.addMall(mallDTO));

    }

    @Test
    void when_updateMallWithIncorrectId_expect_throwIllegalArgumentException()
    {
        MallDTO mDTO = new MallDTO("test","test",3,"desc");
        assertThrows(IllegalArgumentException.class ,()->ms.updateMall(-1,mDTO));
    }
    @Test
    void when_updateMallWithIncorrectData_expect_throwIllegalArgumentException()
    {
        MallDTO mDTO = new MallDTO("",null,3,"desc");
        assertThrows(IllegalArgumentException.class ,()->ms.updateMall(-1,mDTO));
    }

    @Test
    void When_DeleteMall_expect_returningDeletedsuccessfully()
    {
        when(dataBaseService.DeleteMall(1)).thenReturn("Deleted successfully");

        assertEquals("Deleted successfully",ms.deleteMall(1));
    }
    @Test
    void deleteMall() {

        assertThrows(IllegalArgumentException.class , ()->ms.deleteMall(-1));

        //test
//
//        Mall mall = ms.getMall(2);
//        ms.deleteMall(2);
//        mall=ms.getMall(2);
//        assertNull(mall);
    }

}