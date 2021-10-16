package net.thexcoders.aladin_bot_backend.nlp_models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NLPModelsTest {

    @Test
    void testFileExists(){
        NLPModels model = new NLPModels();
        assertTrue(model.testFile(model.fileName));
    }

}