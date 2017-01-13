package org.dronezone.garagedoor;

import com.pi4j.io.gpio.*;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Garage Door Configuration loaded by Spring boot
 *
 * @author Nicholas Drone
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(GarageDoorProperties.class)
public class GarageDoorConfiguration
{
    @Bean
    @Profile("!nopi")
    @RefreshScope
    public GarageDoorService garageDoorService(GarageDoorProperties garageDoorProperties)
    {
        return new GarageDoorServiceImpl(garageDoorProperties, GpioFactory.getInstance());
    }

    @Bean
    @Profile("nopi")
    @RefreshScope
    public GarageDoorService garageDoorServiceNoPi(GarageDoorProperties garageDoorProperties)
    {
        return new GarageDoorServiceImpl(garageDoorProperties, getMockGpio());
    }

    /**
     * Used to in conjunction with Mockito so that I can start the spring boot server without the required
     * files for  Pi4J and wiringPi to be on the file system. Hence why this is only ran
     * when the 'nopi' profile is selected.
     *
     * @return
     */
    private GpioController getMockGpio()
    {
        GpioController gpioController = Mockito.mock(GpioController.class);
        GpioPinDigitalOutput gpioPinDigitalOutput = Mockito.mock(GpioPinDigitalOutput.class);
        Mockito.when(gpioController
                .provisionDigitalOutputPin(Matchers.any(Pin.class), Matchers.any(PinState.class)))
                .thenReturn(gpioPinDigitalOutput);

        Mockito.when(gpioPinDigitalOutput.getPin()).thenReturn(Mockito.mock(Pin.class));
        Mockito.when(gpioPinDigitalOutput.getState()).thenReturn(PinState.HIGH)
                .thenReturn(PinState.LOW).thenReturn(PinState.LOW).thenReturn(PinState.HIGH);

        return gpioController;
    }
}
