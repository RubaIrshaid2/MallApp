package com.mall.mallapp.resources;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.mall.mallapp.DBConfig.AerospikeDB;
import com.mall.mallapp.model.Mall;
import com.mall.mallapp.service.MallService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("/malls")
public class MallResource {

    MallService ms = new MallService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mall> getMalls(){
        return ms.getAllMalls();
    }

    @GET
    @Path("/{mall_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mall getMall(@PathParam("mall_id") int mall_id)
    {
        return ms.getMall(mall_id);
    }

}
