package net.thexcoders.aladin_bot_backend.controllers;

import net.thexcoders.aladin_bot_backend.models.History;
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.CategorizerImpl;
import net.thexcoders.aladin_bot_backend.repositories.HistoryRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HistoryController.class)
@AutoConfigureMockMvc
class HistoryControllerTest {

    private static final String TEST_ID = "61d23e6aa9430d724cccff23";
    private static final History testHistory = new History(
            TEST_ID,
            "How are you doing today?",
            "en",
            new CategorizerImpl.CategoryResult("greeting", 0.2345, "How are you doing today"),
            "waiting",
            new Date()
    );

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryRepository repository;

@Test
public void testHistoryStateChange() throws Exception {
    // préparation du faux résultat de l'appel
    when(repository.findById(TEST_ID)).thenReturn(Optional.of(testHistory));

    // vérification du résultat avant modification
    assertEquals(repository.findById(TEST_ID).get().getState(), History.WAITING);

    // préparation des donnés de la modification
    JSONObject data = new JSONObject();
    data.put("state", 0);
    data.put("id", TEST_ID);

    // appel de l'API responsable de la modification
    this.mockMvc.perform(post("/API/history/state")
            .content(data.toString())
            .contentType("application/json"))
            .andExpect(status().isOk());

    // verification de l'exactitude des données après modification
    assertEquals(repository.findById(TEST_ID).get().getState(), History.VALIDATED);
    assertEquals(repository.findById(TEST_ID).get().getId(), TEST_ID);
}


    @Test
    public void testHistoryList() throws Exception {
        // préparation du faux résultat de l'appel
        when(repository.findAll()).thenReturn(Collections.singletonList(testHistory));

        // vérification du résultat avant modification
        assertEquals(repository.findAll().size(), 1);
        assertEquals(repository.findAll().get(0).getId(), TEST_ID);

        this.mockMvc.perform(get("/API/history/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult result) throws Exception {
                        String res = result.getResponse().getContentAsString();
                        JSONArray jsonArray = new JSONArray(res);
                        System.err.println(jsonArray);
                        assertEquals(jsonArray.length(),1);
                        assertEquals(jsonArray.getJSONObject(0).get("id"),TEST_ID);
                    }
                });


    }

}