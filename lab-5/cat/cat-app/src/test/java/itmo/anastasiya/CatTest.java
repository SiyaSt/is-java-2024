package itmo.anastasiya;

import itmo.anastasiya.client.OwnerClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CatApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
class CatTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private OwnerClient ownerClient;


    @Test
    @Sql("clean.sql")
    public void findCat()
            throws Exception {

        mvc.perform(get("/api/cats/1?owner=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(" {" +
                        "        \"id\": 1," +
                        "        \"name\": \"dd\"," +
                        "        \"birthdayDate\": \"2016-07-26\"," +
                        "        \"breed\": \"persian\"," +
                        "        \"color\": \"BLACK\"," +
                        "        \"ownerId\": 1" +
                        "    }"));
    }

    @Test
    @Sql("clean.sql")
    public void findAllBlackCats()
            throws Exception {

        mvc.perform(get("/api/cats/filter?color=BLACK&owner=1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "\"cats\":" +
                        "[{" +
                        "\"id\":1,\"name\":\"dd\"," +
                        "\"birthdayDate\":\"2016-07-26\"," +
                        "\"breed\":\"persian\"," +
                        "\"color\":\"BLACK\"," +
                        "\"ownerId\":1" +
                        "}]}"));
    }

    @Test
    @Sql("clean.sql")
    public void findAllTigerCats()
            throws Exception {

        mvc.perform(get("/api/cats/filter?breed=tiger&owner=1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "\"cats\":" +
                        "[{" +
                        "\"id\":2," +
                        "\"name\":\"dd\"," +
                        "\"birthdayDate\":\"2016-07-26\"," +
                        "\"breed\":\"tiger\"," +
                        "\"color\":\"RED\"," +
                        "\"ownerId\":1" +
                        "}]}"));
    }

}