package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetectorImpl;
import net.thexcoders.aladin_bot_backend.nlp_models.sentence.SentenceDetectorImplEng;
import net.thexcoders.aladin_bot_backend.nlp_models.sentence.SentenceDetectorImplFra;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.Tokenizer;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.TokenizerImplEng;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.TokenizerImplFra;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NLPModelsTest {

    LangDetectorImpl langDetector = new LangDetectorImpl();
    TokenizerImplEng tokenizerEng = new TokenizerImplEng();
    TokenizerImplFra tokenizerFra = new TokenizerImplFra();
    SentenceDetectorImplEng sentenceDetectorEng = new SentenceDetectorImplEng();
    SentenceDetectorImplFra sentenceDetectorFra = new SentenceDetectorImplFra();


    @Test
    void testFilesExists() {
        assertTrue(langDetector.fileExists());
        assertTrue(tokenizerEng.fileExists());
        assertTrue(tokenizerFra.fileExists());
        assertTrue(sentenceDetectorEng.fileExists());

        // this model doesn't exist.
        // we have used a split using regex instead
        assertFalse(sentenceDetectorFra.fileExists());
    }


}