package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.chunker.ChunkerEng;
import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetector;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.TokenizerEng;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NLPModelTest {

    LangDetector langDetector = new LangDetector();
    ChunkerEng chunkerEng = new ChunkerEng();
    TokenizerEng tokenizerEng = new TokenizerEng();

    @Test
    void testFile() {
        assertTrue(langDetector.fileExists());
        assertTrue(chunkerEng.fileExists());
        assertTrue(tokenizerEng.fileExists());
    }
}