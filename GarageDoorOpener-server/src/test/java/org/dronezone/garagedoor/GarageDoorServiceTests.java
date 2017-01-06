package org.dronezone.garagedoor;

import com.pi4j.io.gpio.*;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * @author Nicholas Drone
 */
public class GarageDoorServiceTests
{
    private static final int    ACTION_PIN           = 7;
    private static final String GOOD_KEYCODE         = "1234";
    private static final String BAD_KEY_CODE_ENTERED = "Bad KeyCode Entered.";

    private GarageDoorServiceImpl garageDoorService;
    private GpioPinDigitalOutput  gpioPinDigitalOutput;

    @Before
    public void setUp()
    {
        GpioController gpioController = Mockito.mock(GpioController.class);
        gpioPinDigitalOutput = Mockito.mock(GpioPinDigitalOutput.class);
        Mockito.when(gpioController.provisionDigitalOutputPin(Matchers.eq(RaspiPin.GPIO_07),
                Matchers.eq(PinState.HIGH))).thenReturn(gpioPinDigitalOutput);

        GarageDoorProperties garageDoorProperties = new GarageDoorProperties();
        garageDoorProperties.setKeycodeSecret(GOOD_KEYCODE);
        garageDoorProperties.setActionPin(ACTION_PIN);
        garageDoorProperties.setActionPinState(PinState.HIGH);
        garageDoorService = new GarageDoorServiceImpl(garageDoorProperties, gpioController);

        Mockito.verify(gpioPinDigitalOutput, Mockito.times(1))
                .setShutdownOptions(Matchers.eq(true), Matchers.eq(PinState.HIGH),
                        Matchers.eq(PinPullResistance.OFF));

        Mockito.when(gpioPinDigitalOutput.getPin()).thenReturn(Mockito.mock(Pin.class));
        Mockito.when(gpioPinDigitalOutput.getState()).thenReturn(PinState.HIGH)
                .thenReturn(PinState.LOW).thenReturn(PinState.LOW).thenReturn(PinState.HIGH);
    }

    @Test
    public void testDoorOperation()
    {
        MatcherAssert
                .assertThat("Good KeyCode Entered.", garageDoorService.doorOperation(GOOD_KEYCODE),
                        org.hamcrest.Matchers.is(true));
        Mockito.verify(gpioPinDigitalOutput, Mockito.times(2)).toggle();
    }

    @Test
    public void testDoorOperationBadKeyCode()
    {
        MatcherAssert.assertThat(BAD_KEY_CODE_ENTERED, garageDoorService.doorOperation("123"),
                org.hamcrest.Matchers.is(false));
        Mockito.verify(gpioPinDigitalOutput, Mockito.never()).toggle();
    }

    @Test
    public void testDoorOperationNullKeyCode()
    {
        MatcherAssert.assertThat(BAD_KEY_CODE_ENTERED, garageDoorService.doorOperation(null),
                org.hamcrest.Matchers.is(false));
        Mockito.verify(gpioPinDigitalOutput, Mockito.never()).toggle();
    }
}
