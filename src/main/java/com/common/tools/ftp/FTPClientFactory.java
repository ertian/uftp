package com.common.tools.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by udbwcso on 2016/3/14.
 */
public class FTPClientFactory extends BasePooledObjectFactory<FTPClient> {

    private final String host;
    private final int port;
    private final String username;
    private final String password;

    public FTPClientFactory(final String host, final int port, final String username, final String password){
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * Creates an object instance, to be wrapped in a {@link PooledObject}.
     * <p>This method <strong>must</strong> support concurrent, multi-threaded
     * activation.</p>
     *
     * @return an instance to be served by the pool
     * @throws Exception if there is a problem creating a new instance,
     *                   this will be propagated to the code requesting an object.
     */
    public FTPClient create() throws Exception {
        FTPClient client = new FTPClient();
        client.connect(host, port);
        client.login(username, password);
        return client;
    }

    /**
     * Wrap the provided instance with an implementation of
     * {@link PooledObject}.
     *
     * @param obj the instance to wrap
     * @return The provided instance, wrapped by a {@link PooledObject}
     */
    public PooledObject<FTPClient> wrap(FTPClient obj) {
        return new DefaultPooledObject<FTPClient>(obj);
    }

    /**
     * destroy object
     */
    @Override
    public void destroyObject(PooledObject<FTPClient> p) throws Exception {
        FTPClient ftpClient = p.getObject();
        ftpClient.logout();
        ftpClient.disconnect();
    }
}
