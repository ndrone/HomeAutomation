package org.dronezone.garagedoor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.pi4j.io.gpio.PinState.HIGH;
import static com.pi4j.io.gpio.RaspiPin.GPIO_07;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class GarageDoorServiceTest
{
    private GarageDoorServiceImpl garageDoorService;
    private GpioPinDigitalOutput  gpioPinDigitalOutput;

    @Before
    public void setup()
    {
        GpioController gpioController = Mockito.mock(GpioController.class);
        gpioPinDigitalOutput = Mockito.mock(GpioPinDigitalOutput.class);
        when(gpioController.provisionDigitalOutputPin(eq(GPIO_07), eq(HIGH)))
                .thenReturn(gpioPinDigitalOutput);

        garageDoorService = new GarageDoorServiceImpl(gpioController);

        Assert.assertNotNull(gpioPinDigitalOutput);
        verify(gpioPinDigitalOutput, times(1))
                .setShutdownOptions(eq(true), eq(PinState.HIGH), eq(PinPullResistance.OFF));
    }

    @Test
    public void testDoorOperation() throws InterruptedException
    {
        garageDoorService.doorOperation();
        verify(gpioPinDigitalOutput, times(1)).low();
        verify(gpioPinDigitalOutput, times(1)).high();
    }
}
