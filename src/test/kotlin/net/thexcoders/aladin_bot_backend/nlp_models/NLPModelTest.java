package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetectorImpl;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.TokenizerImplEng;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NLPModelTest {

    LangDetectorImpl langDetector = new LangDetectorImpl();
    TokenizerImplEng tokenizerEng = new TokenizerImplEng();

    @Test
    void testFile() {
        assertTrue(langDetector.fileExists());
        assertTrue(tokenizerEng.fileExists());
    }
}