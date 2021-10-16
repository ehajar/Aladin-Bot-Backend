package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.exceptions.AladinException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NLPModelsTest {

    @Test
    void testFileExists()  {
        LangDetector model = null;
        try {
            model = new LangDetector();
        } catch (AladinException e) {
            e.printStackTrace();
        }
        assertTrue(model.testFile(model.fileName));
    }

}