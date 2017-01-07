package org.dronezone.web;

import org.dronezone.garagedoor.GarageDoorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Nicholas Drone
 */
@RunWith(SpringRunner.class)
@WebMvcTest(KeyCodeRestController.class)
public class KeyCodeRestControllerTests
{
    @Autowired
    private MockMvc           mockMvc;
    @MockBean
    private GarageDoorService garageDoorService;

    @Before
    public void setUp()
    {
        Mockito.reset(garageDoorService);
    }

    @Test
    public void submitKeycode() throws Exception
    {
        Mockito.when(garageDoorService.doorOperation(Mockito.anyString())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/keycode/1234"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(garageDoorService, Mockito.times(1)).doorOperation("1234");
    }

    @Test
    public void keyCodeThrownException() throws Exception
    {
        Mockito.when(garageDoorService.doorOperation(Mockito.anyString())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/keycode/123"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        Mockito.verify(garageDoorService, Mockito.times(1)).doorOperation("123");
    }
}
