package com.mall.mallapp.resources;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.mall.mallapp.DBConfig.AerospikeDB;
import com.mall.mallapp.model.Mall;
import com.mall.mallapp.service.MallService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.soap.Text;

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mall addMall(Mall mall)
    {
        System.out.println("dfkdslkfjsl");
        return ms.add_Mall(mall);
    }

    @PUT
    @Path("/{mall_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateMall(@PathParam("mall_id") int id ,  Mall mall)
    {
        ms.updateMall(id , mall);
    }

    @DELETE
    @Path("/{mall_id}")
    @Produces("text/plain")
    public String deleteMall(@PathParam("mall_id") int id)
    {
        return ms.deleteMall(id);
    }

}
