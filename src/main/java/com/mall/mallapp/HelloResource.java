package com.mall.mallapp;

import com.aerospike.client.AerospikeClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.Policy;


@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {

//        try{
//            System.out.println("hello from the other sideeeeeeeeeeeeeeeeeeeeeeee");
//            AerospikeClient client = new AerospikeClient("127.0.0.1" , 3000);
//            System.out.println("ohhhhhhhhhh good every thing going good");
//            Key key = new Key("test", "demo", "key1");
//            System.out.println(key);
//
//            Policy policy = new Policy();
//            policy.socketTimeout = 300;
//
//            boolean exists = client.exists(policy, key);
//
//            // Do something
//            System.out.format("Exists: %s", exists);
//
//            Record record = client.getHeader(policy, key);
//
//// Do something
//            System.out.format("Record: %s", record);
//
//            Record record2 = client.get(policy, key);
//
//// Do something
//            System.out.format("Record2: %s", record2.bins);
//
//            Record record3 = client.get(policy, key, "foo", "bar");
//
//// Do something
//            System.out.format("Record: %s", record3.bins);
//
//        }
//        catch(Exception e)
//        {
//            System.out.println("we are not here");
//            System.out.println(e);
//        }
        return "Hello from the other sideEEEE!";
    }
}