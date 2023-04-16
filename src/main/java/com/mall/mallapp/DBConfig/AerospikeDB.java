/**
 *The AerospikeDB class provides a wrapper for connecting to Aerospike database.
 *It contains a static method for getting a shared instance of the AerospikeClient object.
 */

package com.mall.mallapp.DBConfig;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;


public class AerospikeDB {

    private static AerospikeClient client = new AerospikeClient("127.0.0.1", 3000);

    /**
     * Private constructor to prevent the instantiation of the class.
     */
    private AerospikeDB() {
    }

    /**
     * Returns a shared instance of the AerospikeClient object.
     *
     * @return AerospikeClient instance
     */
    public static AerospikeClient getClient()
    {
        return client;
    }

}
