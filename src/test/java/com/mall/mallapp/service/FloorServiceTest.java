package com.mall.mallapp.service;

import com.mall.mallapp.dto.FloorDTO;
import com.mall.mallapp.exception.NotFoundException;
import com.mall.mallapp.model.Floor;
import com.mall.mallapp.repository.FloorRepo;
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

class FloorServiceTest {

    @InjectMocks
    FloorService fs;
    @Mock
    FloorRepo dataBaseService;
    @BeforeEach
    void setUp() {

        dataBaseService = mock(FloorRepo.class);
        fs = new FloorService(dataBaseService);
    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void When_floorsAreExist_Expect_returningThefloors() throws NotFoundException {
        List<Floor> list = new ArrayList<>();
        list.add(new Floor(1 , 1 , 1 ,"shoes",10));
        list.add(new Floor(2,1,3,"clothes",25));

        when(dataBaseService.getFloors(1)).thenReturn(list);

        List<FloorDTO> actualList = fs.getFloors(1);

        List<FloorDTO> Expectedlist = new ArrayList<>();
        Expectedlist.add(new FloorDTO( 1 , 1 ,"shoes",10));
        Expectedlist.add(new FloorDTO(1,3,"clothes",25));

        assertEquals(Expectedlist , actualList);

        verify(dataBaseService).getFloors(1);

    }
    @Test
    void when_noFloors_expect_throwNotFoundException()
    {
        List<Floor> list = new ArrayList<>();

        when(dataBaseService.getFloors(1)).thenReturn(list);


        assertThrows(NotFoundException.class , ()->fs.getFloors(1));

        verify(dataBaseService).getFloors(1);
    }

    @Test
    void when_2FloorsExist_expect_sizeEqualTo2() throws NotFoundException {
        List<Floor> list = new ArrayList<>();
        list.add(new Floor(1 , 1 , 1 ,"shoes",10));
        list.add(new Floor(2,1,3,"clothes",25));

        when(dataBaseService.getFloors(1)).thenReturn(list);

        List<FloorDTO> actualList = fs.getFloors(1);

        assertEquals(2 , actualList.size());

        verify(dataBaseService).getFloors(1);
    }

    @Test
    void when_floorReturned_expect_beingfloorDTO() throws NotFoundException {
        List<Floor> list = new ArrayList<>();
        list.add(new Floor(1 , 1 , 1 ,"shoes",10));
        list.add(new Floor(2,1,3,"clothes",25));

        when(dataBaseService.getFloors(1)).thenReturn(list);

        List<FloorDTO> actualList = fs.getFloors(1);

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
    void When_FloorIdIsCorrect_expect_returningTheFloor() throws NotFoundException {
        Floor f = new Floor(1 , 1 , 1 ,"shoes",10);
        when(dataBaseService.getFloor(1,1)).thenReturn(f);
        FloorDTO Expected = new FloorDTO(1 , 1 ,"shoes",10);
        FloorDTO actual = fs.getFloor(1,1);
        assertEquals(Expected,actual);
    }

    @Test
    void when_getfloorIsCalled_expect_returningDTOFloor() throws NotFoundException {
        Floor f = new Floor(1 , 1 , 1 ,"shoes",10);
        when(dataBaseService.getFloor(1,1)).thenReturn(f);
        assertInstanceOf(FloorDTO.class , fs.getFloor(1,1));
    }

    @Test
    void when_gettingFloorWithWrongId_expect_IllegalArgumentException()
    {
        when(dataBaseService.getFloor(-1 , -1)).thenReturn(null);
        assertThrows(IllegalArgumentException.class ,()->fs.getFloor(-1,-1));
    }
    @Test
    void when_addingFloorWithEmptyFields_expect_IllegalArgumentException()
    {
        FloorDTO F = new FloorDTO( 1 , 1 ,"",10);
        assertThrows(IllegalArgumentException.class , ()->fs.addFloor(1,F));
    }

    @Test
    void when_updateFloorWithIncorrectId_expect_throwIllegalArgumentException()
    {
        FloorDTO fDTO = new FloorDTO( 1 , 1 ,"shoes",10);
        assertThrows(IllegalArgumentException.class ,()->fs.updateFloor(-1,-1,fDTO));
    }
    @Test
    void when_updateFloorWithIncorrectData_expect_throwIllegalArgumentException()
    {
        FloorDTO fDTO = new FloorDTO( 1 , 1 ,"",10);
        assertThrows(IllegalArgumentException.class ,()->fs.updateFloor(1,1,fDTO));
    }

    @Test
    void When_DeleteFloor_expect_returningDeletedsuccessfully()
    {
        when(dataBaseService.deleteFloor(1)).thenReturn("Deleted successfully");
        assertEquals("Deleted successfully",fs.deleteFloor(1));
    }
    @Test
    void deleteFloor() {
        assertThrows(IllegalArgumentException.class , ()->fs.deleteFloor(-1));
    }

}