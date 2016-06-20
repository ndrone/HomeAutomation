package org.dronezone.garagedoor;

import com.pi4j.io.gpio.GpioFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GarageDoorProperties.class)
public class GarageDoorConfiguration
{
    @Bean
    public GarageDoorService garageDoorService()
    {
        return new GarageDoorServiceImpl(GpioFactory.getInstance());
    }
}
