package net.thexcoders.aladin_bot_backend.controllers;

import net.thexcoders.aladin_bot_backend.converters.LangConverter;
import net.thexcoders.aladin_bot_backend.models.Responses;
import net.thexcoders.aladin_bot_backend.repositories.ResponseRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LangController.class)
@AutoConfigureMockMvc
class LangControllerTest {

    private static final String TEST_ENGLISH_MESSAGE = "Hello, How are you doing?";
    private static final String TEST_UNKNOWN_MESSAGE = "Hallo, wie geht's dir heute?";
    private static final String TEST_FRENCH_MESSAGE = "Bonsoir j'espère que vous allez bien ce soir?";

    private static final Responses myResponse = new Responses("detectedLang", "fr")
            .add("").setLang("en").add("");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResponseRepository responseRepo;

    @Test
    public void detectFrenchLanguage() throws Exception {
        checkLanguage(TEST_FRENCH_MESSAGE, LangConverter.FR);
    }

    @Test
    public void detectEnglishLanguage() throws Exception {
        checkLanguage(TEST_ENGLISH_MESSAGE, LangConverter.EN);
    }

    @Test
    public void detectUnknownLanguage() throws Exception {
        checkLanguage(TEST_UNKNOWN_MESSAGE, LangConverter.UNKNOWN);
    }


    public void checkLanguage(String message, int expectedLanguage) throws Exception {

        // préparation du faux résultat de l'appel
        when(responseRepo.findById("detectedLang")).thenReturn(Optional.of(myResponse));
        when(responseRepo.findById("unknownLanguage")).thenReturn(Optional.of(myResponse));

        // préparation des donnés de la modification
        JSONObject data = new JSONObject();
        data.put("message", message);

        // appel de l'API responsable de la modification
        this.mockMvc.perform(post("/API/language/get")
                .content(data.toString())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult result) throws Exception {
                        String jsonResponse = result.getResponse().getContentAsString();
                        JSONObject json = new JSONObject(jsonResponse);
                        int lang = (int) json.get("langCode");
                        // verification de l'exactitude des données
                        assertEquals(expectedLanguage, lang);
                    }
                });
    }
}