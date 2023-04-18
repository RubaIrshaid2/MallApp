package com.mall.mallapp.dBConfig;
import com.aerospike.client.AerospikeClient;
/**
 *The AerospikeDB class provides a wrapper for connecting to Aerospike database.
 *It contains a static method for getting a shared instance of the AerospikeClient object.
 */
public class AerospikeDB {

    private static String hostName = "127.0.0.1";
    private static int port = 3000;
    private static AerospikeClient client = new AerospikeClient(hostName, port);

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
