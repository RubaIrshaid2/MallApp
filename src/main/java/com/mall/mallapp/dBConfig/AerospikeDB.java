package com.mall.mallapp.dBConfig;
import com.aerospike.client.AerospikeClient;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *The AerospikeDB class provides a wrapper for connecting to Aerospike database.
 *It contains a static method for getting a shared instance of the AerospikeClient object.
 */
public class AerospikeDB {
    private static AerospikeClient client = null ;
    static Properties props = new Properties();
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
        if(client == null)
        {
            try (FileInputStream fis = new FileInputStream("config.properties")) {
                props.load(fis);

                String hostName = props.getProperty("db.hostName");
                int port = Integer.parseInt(props.getProperty("db.port"));

                client = new AerospikeClient(hostName, port);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }

}
