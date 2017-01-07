package org.dronezone.garagedoor;

import com.pi4j.io.gpio.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Nicholas Drone
 */
public class GarageDoorServiceTests
{
    private static final int ACTION_PIN = 7;
    private static final  String GOOD_KEYCODE = "1234";
    private static final String BAD_KEY_CODE_ENTERED = "Bad KeyCode Entered.";

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
        garageDoorProperties.setKeycodeSecret(GOOD_KEYCODE);
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
    public void testDoorOperation()
    {
        assertThat("Good KeyCode Entered.", garageDoorService.doorOperation(GOOD_KEYCODE),
                is(true));
        verify(gpioPinDigitalOutput, times(2)).toggle();
    }

    @Test
    public void testDoorOperationBadKeyCode()
    {
        assertThat(BAD_KEY_CODE_ENTERED, garageDoorService.doorOperation("123"), is(false));
        verify(gpioPinDigitalOutput, never()).toggle();
    }

    @Test
    public void testDoorOperationNullKeyCode()
    {
        assertThat(BAD_KEY_CODE_ENTERED, garageDoorService.doorOperation(null), is(false));
        verify(gpioPinDigitalOutput, never()).toggle();
    }
}
