package org.jtar.testcaseTools;

import lombok.extern.slf4j.Slf4j;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

/**
 * @author Allen.Shen
 * @createTime 2023/6/24 15:08
 * @description
 */
@Listeners()
@Slf4j
public class BaseTest {

    @BeforeClass
    public void setup() {
        log.info("-------------预处理----------");
    }

    @AfterClass
    public void teardown(){
        log.info("----------结尾处理-----------");
    }


}
