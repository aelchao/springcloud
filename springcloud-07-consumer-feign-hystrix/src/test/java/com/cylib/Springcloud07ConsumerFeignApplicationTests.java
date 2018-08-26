package com.cylib;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Springcloud07ConsumerFeignApplication.class)
@WebAppConfiguration
public class Springcloud07ConsumerFeignApplicationTests {

	@Test
	public void contextLoads() {
	}

}
