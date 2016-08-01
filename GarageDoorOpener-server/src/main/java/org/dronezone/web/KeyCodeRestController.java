package org.dronezone.web;

import org.dronezone.garagedoor.GarageDoorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest interface so that the user can submit a key code.
 *
 * @author Nicholas Drone
 * @since 1.0
 */
@RestController
public class KeyCodeRestController
{
    private static final Logger LOG = LoggerFactory
        .getLogger(KeyCodeRestController.class);

    private final GarageDoorService garageDoorService;

    @Autowired
    public KeyCodeRestController(GarageDoorService garageDoorService)
    {
        Assert.notNull(garageDoorService, "GarageDoorService can't be null");
        this.garageDoorService = garageDoorService;
    }

    /**
     * The url to where the client submits the key code to.
     * @param requestingKeycode
     * @return
     */
    @RequestMapping(value = "/api/keycode/{requestingKeycode}", method = RequestMethod.POST)
    ResponseEntity<?> submit(@PathVariable String requestingKeycode)
    {
        LOG.debug("web submitted: " + requestingKeycode);
        try
        {
            garageDoorService.doorOperation(requestingKeycode);
            return new ResponseEntity<Object>(true, HttpStatus.OK);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<Object>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
