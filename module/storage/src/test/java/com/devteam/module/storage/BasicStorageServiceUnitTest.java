package com.devteam.module.storage;

import java.io.File;

import com.devteam.module.common.ClientInfo;
import com.devteam.util.io.FileUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class BasicStorageServiceUnitTest extends TestConfig {
    ClientInfo clientInfo = ClientInfo.DEFAULT;

    @Autowired
    IStorageService service;

    @BeforeAll
    static public void beforeClass() throws Exception {
        FileUtil.emptyFolder(new File("build/app"), false);
    }

    @BeforeEach
    public void setup() throws Exception {
    }

    @Test
    public void test() throws Exception {
    }
}
