package org.dronezone.garagedoor;

import org.mockito.Mockito;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GarageDoorProperties.class)
public class GarageDoorTestConfiguration
{
    @Bean
    public GarageDoorService garageDoorService()
    {
        return Mockito.mock(GarageDoorService.class);
    }
}
