package org.dronezone.web;

import org.dronezone.garagedoor.GarageDoorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Nicholas Drone
 */
@RunWith(SpringRunner.class)
@WebMvcTest(KeyCodeRestController.class)
public class KeyCodeRestControllerTests
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GarageDoorService garageDoorService;

    @Before
    public void setup()
    {
        reset(garageDoorService);
    }

    @Test
    public void submitKeycode() throws Exception
    {
        mockMvc.perform(post("/api/keycode/1234")).andExpect(status().isOk());
        verify(garageDoorService, times(1)).doorOperation("1234");
    }

    @Test
    public void keyCodeThrownException() throws Exception
    {
        doThrow(Exception.class).when(garageDoorService).doorOperation("123");

        mockMvc.perform(post("/api/keycode/123")).andExpect(status().isBadRequest());
        verify(garageDoorService, times(1)).doorOperation("123");
    }
}
