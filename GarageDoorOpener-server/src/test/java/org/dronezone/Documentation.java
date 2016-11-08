package org.dronezone;

import org.dronezone.garagedoor.GarageDoorService;
import org.dronezone.web.KeyCodeRestController;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

/**
 * Created by nd26434 on 9/16/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = {KeyCodeRestController.class})
public abstract class Documentation
{
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(
            "target/generated-snippets");
    @MockBean
    protected GarageDoorService              garageDoorService;
    private   RestDocumentationResultHandler document;
    @Autowired
    private   WebApplicationContext          context;
    private   MockMvc                        mockMvc;

    @Before
    public void setUp()
    {
        this.document = document("{method-name}", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation)).alwaysDo(this.document)
                .build();
    }

    public RestDocumentationResultHandler getDocument()
    {
        return document;
    }

    public MockMvc getMockMvc()
    {
        return mockMvc;
    }
}
