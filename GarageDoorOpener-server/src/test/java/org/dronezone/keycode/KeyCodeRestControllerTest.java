package org.dronezone.keycode;

import org.dronezone.GarageDoorOpenerApplication;
import org.dronezone.garagedoor.GarageDoorService;
import org.dronezone.garagedoor.GarageDoorTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        GarageDoorOpenerApplication.class, GarageDoorTestConfiguration.class})
@WebIntegrationTest({"garage.door.keycode-secret=1234"})
public class KeyCodeRestControllerTest
{
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private GarageDoorService     garageDoorService;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        reset(garageDoorService);
    }

    @Test
    public void health() throws Exception
    {
        this.mockMvc.perform(get("/health").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("status", is("UP")));
    }

    @Test
    public void submitGoodKeycode() throws Exception
    {
        this.mockMvc.perform(post("/api/keycode/1234").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("true"));
        verify(garageDoorService, times(1)).doorOperation();
    }

    @Test
    public void submitBadKeycode() throws Exception
    {
        this.mockMvc.perform(post("/api/keycode/123").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(content().string("false"));
        verify(garageDoorService, never()).doorOperation();
    }

    @Test
    public void testServiceThrownException() throws Exception
    {
        doThrow(InterruptedException.class).when(garageDoorService).doorOperation();

        this.mockMvc.perform(post("/api/keycode/1234").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(content().string("false"));
        verify(garageDoorService, times(1)).doorOperation();
    }

    @Test
    public void submitNoKeycode() throws Exception
    {
        this.mockMvc.perform(post("/api/keycode/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());

        this.mockMvc.perform(post("/api/keycode").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }
}
