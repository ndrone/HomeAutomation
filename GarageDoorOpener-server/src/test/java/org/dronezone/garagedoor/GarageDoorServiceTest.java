package org.dronezone.garagedoor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.pi4j.io.gpio.PinState.HIGH;
import static com.pi4j.io.gpio.RaspiPin.GPIO_07;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Nicholas Drone
 */
public class GarageDoorServiceTest
{
    private GarageDoorServiceImpl garageDoorService;
    private GpioPinDigitalOutput gpioPinDigitalOutput;

    @Before
    public void setup()
    {
        GpioController gpioController = mock(GpioController.class);
        gpioPinDigitalOutput = mock(GpioPinDigitalOutput.class);
        when(gpioController.provisionDigitalOutputPin(eq(GPIO_07), eq(HIGH)))
            .thenReturn(gpioPinDigitalOutput);

        GarageDoorProperties garageDoorProperties = new GarageDoorProperties();
        garageDoorProperties.setKeycodeSecret("1234");
        garageDoorProperties.setActionPin(7);
        garageDoorProperties.setActionPinState(PinState.HIGH);
        garageDoorService = new GarageDoorServiceImpl(garageDoorProperties,
            gpioController);

        Assert.assertNotNull(gpioPinDigitalOutput);
        verify(gpioPinDigitalOutput, times(1))
            .setShutdownOptions(eq(true), eq(PinState.HIGH), eq(PinPullResistance.OFF));
    }

    @Test
    public void testDoorOperation() throws Exception
    {
        garageDoorService.doorOperation("1234");
        verify(gpioPinDigitalOutput, times(2)).toggle();
    }

    @Test(expected = Exception.class)
    public void testDoorOperationBadKeyCode() throws Exception
    {
        garageDoorService.doorOperation("123");
        verify(gpioPinDigitalOutput, never()).toggle();
    }

    @Test(expected = Exception.class)
    public void testDoorOperationNullKeyCode() throws Exception
    {
        garageDoorService.doorOperation(null);
        verify(gpioPinDigitalOutput, never()).toggle();
    }
}
