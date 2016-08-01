package org.dronezone.garagedoor;

/**
 * Service class for the garage door. All interactions should go through this class.
 *
 * @author Nicholas Drone
 * @since 1.0
 */
public interface GarageDoorService
{
    /**
     * Used to open and close the garage door.
     * @param keyCode to be challenged by the service before opening the door.
     * @throws Exception if the {@code keyCode} is incorrect or an issue generated from Pi4j
     */
    void doorOperation(String keyCode) throws Exception;
}
