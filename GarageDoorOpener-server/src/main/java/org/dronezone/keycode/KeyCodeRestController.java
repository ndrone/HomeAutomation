package org.dronezone.keycode;

import org.dronezone.garagedoor.GarageDoorProperties;
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

@RestController
public class KeyCodeRestController
{
    private static final Logger LOG = LoggerFactory.getLogger(KeyCodeRestController.class);

    private final String keycode;
    private final GarageDoorService garageDoorService;

    @Autowired
    public KeyCodeRestController(GarageDoorProperties garageDoorProperties, GarageDoorService garageDoorService)
    {
        this.keycode = garageDoorProperties.getKeycodeSecret();
        Assert.notNull(garageDoorService, "GarageDoorService can't be null");
        this.garageDoorService = garageDoorService;
    }

    @RequestMapping(value = "/api/keycode/{requestingKeycode}", method = RequestMethod.POST)
    ResponseEntity<?> submit(@PathVariable String requestingKeycode)
    {
        LOG.debug("keycode submitted: " + requestingKeycode);
        LOG.debug("keycode checked against: " + keycode);
        if (requestingKeycode.equals(keycode))
        {
            try
            {
                garageDoorService.doorOperation();
                return new ResponseEntity<Object>(true, HttpStatus.OK);
            }
            catch (InterruptedException e)
            {
                LOG.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return new ResponseEntity<Object>(false, HttpStatus.BAD_REQUEST);
    }

}
