package org.dronezone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=native"})
public class ConfigServerApplicationTests
{

    @Test
    public void contextLoads()
    {
    }

}
