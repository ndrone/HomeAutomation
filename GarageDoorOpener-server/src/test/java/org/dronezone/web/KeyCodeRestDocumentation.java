package org.dronezone.web;

import org.dronezone.Documentation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by nd26434 on 11/7/2016.
 */
public class KeyCodeRestDocumentation extends Documentation
{
    @Before
    public void setup()
    {
        Mockito.reset(garageDoorService);
    }

    @Test
    public void doorOperationValid() throws Exception
    {
        getMockMvc().perform(post("/api/keycode/1234")).andExpect(status().isOk());
    }

    @Test
    public void doorOperationInvalid() throws Exception
    {
        doThrow(new Exception("Error thrown")).when(garageDoorService).doorOperation(anyString());

        getMockMvc().perform(post("/api/keycode/1234")).andExpect(status().isBadRequest());
    }
}
