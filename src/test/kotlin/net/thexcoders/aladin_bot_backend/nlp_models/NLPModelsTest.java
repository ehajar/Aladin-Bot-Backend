package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NLPModelsTest {

    @Test
    void testFileExists()  {
        LangDetector model = null;
        model = new LangDetector();
        assertTrue(model.testFile(model.fileName));
    }

}