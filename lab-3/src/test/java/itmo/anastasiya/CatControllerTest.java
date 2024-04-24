package itmo.anastasiya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.dto.OwnerDto;
import itmo.anastasiya.entity.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
class CatControllerTest {
    @Autowired
    private MockMvc mvc;


    private ObjectMapper mapper;
    private ObjectWriter ow;
    private CatDto cat;
    private CatDto catRed;

    private OwnerDto owner;

    @BeforeEach
    public void init() {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = mapper.writer().withDefaultPrettyPrinter();
        owner = OwnerDto.builder().name("dd").birthdayDate(Date.from(Instant.now())).build();
        cat = CatDto
                .builder()
                .name("pushok")
                .birthdayDate(Date.from(Instant.now()))
                .breed("persian")
                .color(Color.BLACK)
                .ownerId(1L)
                .build();
        catRed = CatDto
                .builder()
                .name("pushok")
                .birthdayDate(Date.from(Instant.now()))
                .breed("tiger")
                .color(Color.RED)
                .ownerId(1L)
                .build();
    }

    @Test
    public void findCat()
            throws Exception {


        String requestJsonOwner = ow.writeValueAsString(owner);
        String requestJsonCat = ow.writeValueAsString(cat);

        mvc.perform(post("/api/owners")
                        .content(requestJsonOwner)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());


        mvc.perform(post("/api/cats")
                        .content(requestJsonCat)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        var result = mvc.perform(get("/api/cats/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONAssert.assertEquals("{id: 1, name: \"pushok\", breed: \"persian\", color: \"BLACK\", ownerId: 1}",
                result, JSONCompareMode.LENIENT);
    }

    @Test
    public void findOwner()
            throws Exception {

        OwnerDto owner = OwnerDto.builder().name("dd").birthdayDate(Date.from(Instant.now())).build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(owner);

        mvc.perform(post("/api/owners")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        var result = mvc.perform(get("/api/owners/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONAssert.assertEquals("{id: 1, name:  \"dd\"}",
                result, JSONCompareMode.LENIENT);
    }

    @Test
    public void findAllBlackCats()
            throws Exception {

        String requestJsonOwner = ow.writeValueAsString(owner);
        String requestJsonCat = ow.writeValueAsString(cat);
        String requestJsonCatRed = ow.writeValueAsString(catRed);

        mvc.perform(post("/api/owners")
                        .content(requestJsonOwner)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());


        mvc.perform(post("/api/cats")
                        .content(requestJsonCat)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        mvc.perform(post("/api/cats")
                        .content(requestJsonCatRed)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        var result = mvc.perform(get("/api/cats/filter?color=BLACK"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONAssert.assertEquals("[{id: 1, name: \"pushok\", breed: \"persian\", color: \"BLACK\", ownerId: 1}]",
                result, JSONCompareMode.LENIENT);
    }

    @Test
    public void findAllTigerCats()
            throws Exception {

        String requestJsonOwner = ow.writeValueAsString(owner);
        String requestJsonCat = ow.writeValueAsString(cat);
        String requestJsonCatRed = ow.writeValueAsString(catRed);

        mvc.perform(post("/api/owners")
                        .content(requestJsonOwner)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());


        mvc.perform(post("/api/cats")
                        .content(requestJsonCat)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        mvc.perform(post("/api/cats")
                        .content(requestJsonCatRed)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        mvc.perform(get("/api/cats/filter?breed=tiger"))
                .andExpect(status().isOk())
                .andDo(print());
//        JSONAssert.assertEquals("[{id: 2, name: \"pushok\",breed: \"tiger\", color: \"RED\", ownerId: 1}]",
//                result, JSONCompareMode.LENIENT);
    }

}