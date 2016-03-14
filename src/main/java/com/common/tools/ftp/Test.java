package com.common.tools.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * Created by udbwcso on 2016/3/14.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ObjectPool<FTPClient> pool = new GenericObjectPool<FTPClient>(new FTPClientFactory("192.168.1.134", 21, "platform", "platform"));
        for (int i = 0; i < 10; i++) {
            FTPClient client = pool.borrowObject();
            System.out.println(client.getSystemType());
        }
    }
}
