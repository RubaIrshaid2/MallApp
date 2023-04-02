package com.mall.mallapp.service;

import com.mall.mallapp.model.Mall;

import java.util.ArrayList;
import java.util.List;

public class MallService {

    public List<Mall> getAllMalls()
    {
        Mall m1 = new Mall(1,"rMall","hebron",80,"in the main street");
        Mall m2 = new Mall(2,"r2Mall","hebron2",802,"in the main street2");
        List<Mall> ml = new ArrayList<>();
        ml.add(m1);
        ml.add(m2);

        return ml;
    }
}
