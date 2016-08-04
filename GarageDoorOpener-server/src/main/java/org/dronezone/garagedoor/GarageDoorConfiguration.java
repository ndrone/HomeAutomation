package org.dronezone.garagedoor;

import com.pi4j.io.gpio.GpioFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Garage Door Configuration loaded by Spring boot
 * @author Nicholas Drone
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(GarageDoorProperties.class)
public class GarageDoorConfiguration
{
    @Bean
    @RefreshScope
    public GarageDoorService garageDoorService(GarageDoorProperties garageDoorProperties)
    {
        return new GarageDoorServiceImpl(garageDoorProperties, GpioFactory.getInstance());
    }
}
