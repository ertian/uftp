package com.common.tools.ftp;

/**
 * Created by udbwcso on 2016/3/14.
 */
public class FTPClientTest implements Runnable{
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(new FTPClientTest()).start();
        }
    }

    public void run() {
        FTPClientUtil clientUtil = FTPClientUtil.getInstance();
        System.out.println(clientUtil.getWorkingDirectory());
    }
}
