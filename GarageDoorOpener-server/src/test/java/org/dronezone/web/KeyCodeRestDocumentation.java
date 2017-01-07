package org.dronezone.web;

import org.dronezone.garagedoor.GarageDoorService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
    private GarageDoorService     garageDoorService;
    @Autowired
    private WebApplicationContext context;
    private MockMvc               mockMvc;

    private static final String URL = "/api/keycode/1234";

    @Before
    public void setUp()
    {
        RestDocumentationResultHandler document = MockMvcRestDocumentation.document("{method-name}",
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation))
                .alwaysDo(document).build();
        Mockito.reset(garageDoorService);
    }

    @Test
    public void doorOperationValid() throws Exception
    {
        Mockito.when(garageDoorService.doorOperation(Matchers.anyString())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post(URL))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void doorOperationInvalid() throws Exception
    {
        Mockito.when(garageDoorService.doorOperation(Matchers.anyString())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post(URL))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
