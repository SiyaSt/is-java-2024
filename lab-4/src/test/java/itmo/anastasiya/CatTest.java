package itmo.anastasiya;

import itmo.anastasiya.config.SecurityConfig;
import itmo.anastasiya.dto.UserDto;
import itmo.anastasiya.user.MyUserDetail;
import itmo.anastasiya.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@ContextConfiguration(classes = SecurityConfig.class)
class CatTest {
    @Autowired
    private MockMvc mvc;

    private UserDto admin;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void init() {
        admin = userService.findByUsername("neaboba");
    }

    @Test
    @Sql("clean.sql")
    public void findCat()
            throws Exception {

        mvc.perform(get("/api/cats/1")
                        .with(user(new MyUserDetail(admin))))
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
    public void findOwner()
            throws Exception {

        mvc.perform(get("/api/owners/1")
                        .with(user(new MyUserDetail(admin))))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "        \"id\": 1," +
                        "        \"name\": \"dd\"," +
                        "        \"birthdayDate\": \"2016-07-26\"" +
                        "    }"));
    }

    @Test
    @Sql("clean.sql")
    public void findAllBlackCats()
            throws Exception {

        mvc.perform(get("/api/cats/filter?color=BLACK")
                        .with(user(new MyUserDetail(admin))))
                .andExpect(status().isOk())
                .andExpect(content().json("[ {" +
                        "        \"id\": 1," +
                        "        \"name\": \"dd\"," +
                        "        \"birthdayDate\": \"2016-07-26\"," +
                        "        \"breed\": \"persian\"," +
                        "        \"color\": \"BLACK\"," +
                        "        \"ownerId\": 1" +
                        "    }]"));
    }

    @Test
    @Sql("clean.sql")
    public void findAllTigerCats()
            throws Exception {

        mvc.perform(get("/api/cats/filter?breed=tiger")
                        .with(user(new MyUserDetail(admin))))
                .andExpect(status().isOk())
                .andExpect(content().json("[ {" +
                        "        \"id\": 2," +
                        "        \"name\": \"dd\"," +
                        "        \"birthdayDate\": \"2016-07-26\"," +
                        "        \"breed\": \"tiger\"," +
                        "        \"color\": \"RED\"," +
                        "        \"ownerId\": 1" +
                        "    }]"));
    }

    @Test
    @Sql("clean.sql")
    public void forbiddenException()
            throws Exception {

        var user = userService.findByUsername("neaboba2");
        mvc.perform(get("/api/cats/1")
                        .with(user(new MyUserDetail(user))))
                .andExpect(status().isBadRequest());
    }

}