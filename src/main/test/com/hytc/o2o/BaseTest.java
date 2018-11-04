package com.hytc.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 用来初始化Spring和Junit整合，启动时候家在Spring容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-web.xml","classpath:spring/spring-redis.xml"})
@WebAppConfiguration("src/main/resources")
public class BaseTest {

}
