package com.mall.mallapp.service;

import com.mall.mallapp.model.Mall;
import org.junit.jupiter.api.*;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MallServiceTest {

    MallService ms;
    @BeforeEach
    void setUp() {
        ms = new MallService();
    }

    @AfterEach
    void tearDown() {
        ms = null;
    }

    @Test
    void getAllMalls() {

        //List<Mall> ml = ms.getAllMalls();

        //test
       // assertEquals(5,ml.size());

        // test
//        boolean b = true;
//        for (Object obj : ml) {
//            if (obj == null) {
//                b=false;
//                break;
//            }
//        }
//
//        assertTrue(b);

        //test

//        boolean sameKind = true;
//        Class<?> firstClass = ml.get(0).getClass();
//        for (Object obj : ml) {
//            if (obj.getClass() != firstClass) {
//                sameKind = false;
//                break;
//            }
//        }
//
//        assertTrue(sameKind);

        //testing exception of databaseconnection


    }

    @Test
    @DisplayName("testing get mall method")
    void getMall() {

        //Mall m = ms.getMall(3);
//        assertAll(
//                ()->assertEquals("City mall",m.getName() ,"here we get the name of the mall"),
//                ()->assertEquals("Hebron",m.getAddress() ,"here we get the address of the mall")
//        );

//        boolean equalMalls = m.equals(new Mall(3,"City mall" ,"Hebron" , 293 , "in the center of the city")) ;
//        assertTrue(equalMalls);

//        assertEquals(3 , m.getId());

      //  assertInstanceOf(Mall.class , m);

        //test
        //Mall m = ms.getMall(30);
     //   Mall m2 = ms.getMall(-1);

//        assertAll(
//                ()->assertNull(m),
//                ()->assertNull(m2)
//        );

    }

    @Test
    void add_Mall() {

        //test

//        Mall mall = new Mall(0,"Ramallah Mall","Ramallah" , 10 , "ramallah desc");
//        mall = ms.add_Mall(mall);
//        Mall returnedMall = ms.getMall(mall.getId());
//        boolean b = mall.equals(returnedMall);

        //assertTrue(b);

        //test

//        Mall mall = null;
//
//        assertThrows(NullPointerException.class , ()->ms.add_Mall(mall));


        //test adding to mall service line for exception


//        Mall mall = new Mall();
//        assertThrows(IllegalArgumentException.class , ()->ms.add_Mall(mall));

    }

    @Test
    @Disabled
    void ExceptionWhenDataBaseIsOff()
    {
        Mall mall = new Mall(0,"Ramallah Mall","Ramallah" , 10 , "ramallah desc");
        //assertThrows(ExceptionInInitializerError.class ,()->ms.add_Mall(mall) );
    }
    @Test
    void updateMall() {

        //test
//        assertThrows(IllegalArgumentException.class ,()->ms.updateMall(-1,new Mall(1,"jenin","jenin",3,"desc")));


        //test
//        Mall mall = new Mall(0,"lacasa Mall","Ramallah" , 10 , "lacasa desc");
//        ms.updateMall(1,mall);
//
//
//        Mall returnedMall = ms.getMall(1);
//        boolean b = mall.equals(returnedMall);

        //assertTrue(b);


        //test

//        Mall mall = new Mall(0,"","Ramallah" , 10 , "lacasa desc");
//
//        assertThrows(IllegalArgumentException.class , ()->ms.updateMall(1,mall));


        //test

//        Mall mall = null;
//
//        assertThrows(NullPointerException.class , ()->ms.updateMall(1,mall));

    }

    @Test
    void deleteMall() {

        //test
//
//        Mall mall = ms.getMall(2);
//        ms.deleteMall(2);
//        mall=ms.getMall(2);
//        assertNull(mall);

        //test

        assertThrows(IllegalArgumentException.class , ()->ms.deleteMall(-1));
    }
}