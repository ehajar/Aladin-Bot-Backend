package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.thexcoders.aladin_bot_backend.converters.LangConverterKt.*;
import static net.thexcoders.aladin_bot_backend.converters.LangConverterKt.converter;
import static org.junit.jupiter.api.Assertions.*;

class LangDetectorTest {
    private LangDetector detector;
    @BeforeEach
    void testLanguageDetection(){
        detector = new LangDetector();
    }

    @Test
    void detectLang() {
        assertEquals("fra",detector.detectLang("Bonjour Comment allez vous ce soir?"));
        assertEquals("eng",detector.detectLang("Hello, How are you doing tonight?"));
        assertEquals("ara",detector.detectLang("مرحبا كيف حالك الليلة؟"));
    }

    @Test
    void testConverter(){
        assertEquals(AR,converter("ara"));
        assertEquals(FR,converter("fra"));
        assertEquals(EN,converter("eng"));
        assertEquals(UNKNOWN,converter("ita"));
    }
}