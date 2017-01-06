package org.dronezone.garagedoor;

import com.pi4j.io.gpio.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Nicholas Drone
 */
public class GarageDoorServiceTests
{
    private static final int ACTION_PIN = 7;

    private GarageDoorServiceImpl garageDoorService;
    private GpioPinDigitalOutput  gpioPinDigitalOutput;

    @Before
    public void setUp()
    {
        GpioController gpioController = mock(GpioController.class);
        gpioPinDigitalOutput = mock(GpioPinDigitalOutput.class);
        when(gpioController.provisionDigitalOutputPin(eq(RaspiPin.GPIO_07), eq(PinState.HIGH)))
                .thenReturn(gpioPinDigitalOutput);

        GarageDoorProperties garageDoorProperties = new GarageDoorProperties();
        garageDoorProperties.setKeycodeSecret("1234");
        garageDoorProperties.setActionPin(ACTION_PIN);
        garageDoorProperties.setActionPinState(PinState.HIGH);
        garageDoorService = new GarageDoorServiceImpl(garageDoorProperties, gpioController);

        verify(gpioPinDigitalOutput, times(1))
                .setShutdownOptions(eq(true), eq(PinState.HIGH), eq(PinPullResistance.OFF));

        when(gpioPinDigitalOutput.getPin()).thenReturn(mock(Pin.class));
        when(gpioPinDigitalOutput.getState()).thenReturn(PinState.HIGH).thenReturn(PinState.LOW)
                .thenReturn(PinState.LOW).thenReturn(PinState.HIGH);
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
