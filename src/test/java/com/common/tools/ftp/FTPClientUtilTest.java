package com.common.tools.ftp;

import org.junit.Test;

/**
 * Created by udbwcso on 2016/3/16.
 */
public class FTPClientUtilTest {

    @Test
    public void getWorkingDirectory() throws Exception {
        System.out.println(FTPClientUtil.getInstance().getWorkingDirectory());
    }
}
