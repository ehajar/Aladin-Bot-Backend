package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetector;
import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetectorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.thexcoders.aladin_bot_backend.converters.LangConverter.*;
import static net.thexcoders.aladin_bot_backend.converters.LangConverter.langToCodeConverter;
import static org.junit.jupiter.api.Assertions.*;

class LangDetectorTest {
    private LangDetector detector;
    @BeforeEach
    void testLanguageDetection(){
        detector = new LangDetectorImpl();
    }

    @Test
    void detectLang() {
        assertEquals("fra",detector.detectLang("Bonjour Comment allez vous ce soir?"));
        assertEquals("eng",detector.detectLang("Hello, How are you doing tonight?"));
        assertEquals("ara",detector.detectLang("مرحبا كيف حالك الليلة؟"));
    }

    @Test
    void testConverter(){
        assertEquals(AR, langToCodeConverter("ara"));
        assertEquals(FR, langToCodeConverter("fra"));
        assertEquals(EN, langToCodeConverter("eng"));
        assertEquals(UNKNOWN, langToCodeConverter("ita"));
    }
}