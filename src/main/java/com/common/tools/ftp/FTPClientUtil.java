package com.common.tools.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by udbwcso on 2016/3/15.
 */
public class FTPClientUtil {

    public static final String FTP_PROPERTIES = "/ftp.properties";


    private FTPClientUtil(){
    }

    public static FTPClientUtil getInstance(){
        return SingletonHolder.instance;
    }

    /**
     * Returns the pathname of the current working directory.
     * @return
     */
    public static String getWorkingDirectory(){
        try {
            FTPClient client = getClientPool().borrowObject();
            String workingDir = client.printWorkingDirectory();
            getClientPool().returnObject(client);
            return workingDir;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ObjectPool<FTPClient> getClientPool(){
        return SingletonHolder.POOL;
    }

    private static class SingletonHolder {

        public static final ObjectPool<FTPClient> POOL;

        static {
            InputStream resourceAsStream = FTPClientUtil.class.getResourceAsStream(FTP_PROPERTIES);
            Properties p = null;
            if (resourceAsStream != null) {
                p = new Properties();
                try {
                    p.load(resourceAsStream);
                } catch (IOException e) {
                } finally {
                    try {
                        resourceAsStream.close();
                    } catch (IOException e) {
                        // Ignored
                    }
                }
            }
            POOL = new GenericObjectPool<FTPClient>(new FTPClientFactory(p));
        }

        public static FTPClientUtil instance = new FTPClientUtil();

    }






}
