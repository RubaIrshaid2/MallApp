package com.mall.mallapp.resources;

import com.mall.mallapp.model.Mall;
import com.mall.mallapp.service.MallService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/malls")
public class MallResource {

    MallService ms = new MallService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mall> getmall()
    {
        return ms.getAllMalls();
    }

}
