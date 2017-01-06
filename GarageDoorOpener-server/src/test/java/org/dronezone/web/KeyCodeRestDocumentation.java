package org.dronezone.web;

import org.dronezone.garagedoor.GarageDoorService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Nicholas Drone
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = {KeyCodeRestController.class})
public class KeyCodeRestDocumentation
{
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(
            "target/generated-snippets");
    @MockBean
    private GarageDoorService              garageDoorService;
    private RestDocumentationResultHandler document;
    @Autowired
    private WebApplicationContext          context;
    private MockMvc                        mockMvc;

    private static final String URL = "/api/keycode/1234";

    @Before
    public void setUp()
    {
        this.document = document("{method-name}", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation)).alwaysDo(this.document)
                .build();
        Mockito.reset(garageDoorService);
    }

    @Test
    public void doorOperationValid() throws Exception
    {
        when(garageDoorService.doorOperation(anyString())).thenReturn(true);
        mockMvc.perform(post(URL)).andExpect(status().isOk());
    }

    @Test
    public void doorOperationInvalid() throws Exception
    {
        when(garageDoorService.doorOperation(anyString())).thenReturn(false);
        mockMvc.perform(post(URL)).andExpect(status().isBadRequest());
    }
}
