package com.mall.mallapp.DBConfig;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.Policy;

public class AerospikeDB {

    private static AerospikeClient client = new AerospikeClient("127.0.0.1", 3000);

    private AerospikeDB() {
    }

    public static AerospikeClient getClient()
    {
        System.out.println("hello in aerospike class get client method");
        Key key = new Key("test", "demo", "key1");
        System.out.println(key);
        return client;
    }

}
