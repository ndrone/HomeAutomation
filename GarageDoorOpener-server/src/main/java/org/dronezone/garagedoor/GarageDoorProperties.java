package org.dronezone.garagedoor;

import com.pi4j.io.gpio.PinState;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties to be used by the {@link GarageDoorService}.
 *
 * @author Nicholas Drone
 * @since 1.0
 */
@ConfigurationProperties(prefix = "garage.door")
public class GarageDoorProperties
{
    public static final int DEFAULT_PIN = 7;
    /**
     * Key code that the client should be challenged against.
     * <STRONG>NOTE:</STRONG> This should be changed in the environment. Spring boot
     * provides plenty of solutions
     */
    private String keycodeSecret = "1234";

    /**
     * Pin the garage door is connected to. That provided the signal to open
     */
    private int actionPin = DEFAULT_PIN;

    /**
     * The rest or default state the pin should be in.
     */
    private PinState actionPinState = PinState.HIGH;

    public String getKeycodeSecret()
    {
        return keycodeSecret;
    }

    public void setKeycodeSecret(String keycodeSecret)
    {
        this.keycodeSecret = keycodeSecret;
    }

    public int getActionPin()
    {
        return actionPin;
    }

    public void setActionPin(int actionPin)
    {
        this.actionPin = actionPin;
    }

    public PinState getActionPinState()
    {
        return actionPinState;
    }

    public void setActionPinState(PinState actionPinState)
    {
        this.actionPinState = actionPinState;
    }
}
