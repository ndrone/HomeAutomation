package org.dronezone.keycode;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.dronezone.GarageDoorOpenerApplication;
import org.dronezone.garagedoor.GarageDoorService;
import org.dronezone.garagedoor.GarageDoorTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;

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

    @Before
    public void setup()
    {
        RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(this.context).build());
        Mockito.reset(garageDoorService);
    }

    @Test
    public void health() throws Exception
    {
        RestAssuredMockMvc.get("/health").then().log().body().assertThat().body("status", is("UP"));
    }

    @Test
    public void submitGoodKeycode() throws Exception
    {
        RestAssuredMockMvc.post("/api/keycode/1234").then().log().body().assertThat()
                .statusCode(200);
        Mockito.verify(garageDoorService, Mockito.times(1)).doorOperation();
    }

    @Test
    public void submitBadKeycode() throws Exception
    {
        RestAssuredMockMvc.post("/api/keycode/123").then().log().body().assertThat()
                .statusCode(400);
        Mockito.verify(garageDoorService, Mockito.never()).doorOperation();
    }

    @Test
    public void testServiceThrownException() throws Exception
    {
        Mockito.doThrow(InterruptedException.class).when(garageDoorService).doorOperation();

        RestAssuredMockMvc.post("/api/keycode/1234").then().log().body().assertThat()
                .statusCode(400);
        Mockito.verify(garageDoorService, Mockito.times(1)).doorOperation();
    }

    @Test
    public void submitNoKeycode() throws Exception
    {
        RestAssuredMockMvc.post("/api/keycode/").then().log().body().assertThat().statusCode(405);
        RestAssuredMockMvc.post("/api/keycode").then().log().body().assertThat().statusCode(405);
    }
}
