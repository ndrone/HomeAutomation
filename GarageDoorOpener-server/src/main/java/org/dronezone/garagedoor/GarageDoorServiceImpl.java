package org.dronezone.garagedoor;

import com.pi4j.io.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class GarageDoorServiceImpl implements GarageDoorService
{
    private static final Logger LOG = LoggerFactory.getLogger(GarageDoorServiceImpl.class);

    private final GpioController gpioController;
    private final GpioPinDigitalOutput garageDoorPin;

    public GarageDoorServiceImpl(GpioController gpioController)
    {
        Assert.notNull(gpioController, "GpioController can't be null");
        this.gpioController = gpioController;
        garageDoorPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_07,
            PinState.HIGH);
        garageDoorPin.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
    }

    @Override
    public void doorOperation() throws InterruptedException
    {
        LOG.debug("interaction with garage door");
        synchronized (this)
        {
            garageDoorPin.low();
            Thread.sleep(1000);
            garageDoorPin.high();
        }
    }
}
