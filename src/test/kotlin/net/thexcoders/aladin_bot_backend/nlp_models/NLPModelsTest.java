package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetector;
import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetectorImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NLPModelsTest {

    @Test
    void testFileExists()  {
        LangDetectorImpl model = new LangDetectorImpl();
        assertTrue(model.testFile(model.fileName));
    }

}