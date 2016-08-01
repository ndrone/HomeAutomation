package org.dronezone.garagedoor;

import com.pi4j.io.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Default implementation of the interface.
 *
 * @author Nicholas Drone
 * @since 1.0
 */
public class GarageDoorServiceImpl implements GarageDoorService
{
    private static final Logger LOG = LoggerFactory
        .getLogger(GarageDoorServiceImpl.class);

    private final String keyCode;
    private final GpioPinDigitalOutput garageDoorPin;

    /**
     * Setting up Service of the challenging {@code keyCode} and how the garage door is connected.
     *
     * @param garageDoorProperties
     * @param gpioController
     */
    public GarageDoorServiceImpl(GarageDoorProperties garageDoorProperties,
        GpioController gpioController)
    {
        Assert.notNull(garageDoorProperties, "GarageDoorProperties can't be null");
        this.keyCode = garageDoorProperties.getKeycodeSecret();
        Pin pin = RaspiPin.getPinByAddress(garageDoorProperties.getActionPin());
        PinState pinState = garageDoorProperties.getActionPinState();

        Assert.notNull(gpioController, "GpioController can't be null");
        garageDoorPin = gpioController.provisionDigitalOutputPin(pin, pinState);
        garageDoorPin.setShutdownOptions(true, pinState, PinPullResistance.OFF);
    }

    @Override
    public void doorOperation(String keyCode) throws Exception
    {
        LOG.debug("interaction with garage door");
        if (this.keyCode.equals(keyCode))
        {
            synchronized (this)
            {
                garageDoorPin.toggle();
                Thread.sleep(1000);
                garageDoorPin.toggle();
            }
        }
        else
        {
            throw new Exception("Invalid key code");
        }
    }
}
