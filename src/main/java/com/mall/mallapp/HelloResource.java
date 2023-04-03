package com.mall.mallapp;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.ScanPolicy;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.mall.mallapp.DBConfig.AerospikeDB;
import com.mall.mallapp.model.Mall;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;


@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {


        return "Hello from the other sideEEEE!";
    }
}