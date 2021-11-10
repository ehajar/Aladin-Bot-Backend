package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetector;
import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetectorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LangDetectorTest {
    private LangDetector detector;
    @BeforeEach
    void testLanguageDetection(){
        detector = new LangDetectorImpl();
    }

    @Test
    void frenchDetector() {
        assertEquals("fra",detector.detectLang("Bonsoir, Comment allez vous ce soir?"));
        assertEquals("fra",detector.detectLang("Bonjour, j'ai faim et je veux manger"));
        assertEquals("fra",detector.detectLang("quelle est la plus belle ville au Maroc."));
    }
    @Test
    void englishDetector() {
        assertEquals("eng",detector.detectLang("Hello, How are you doing tonight?"));
        assertEquals("eng",detector.detectLang("Hi. I am so hungry and I want to eat something nice tonight"));
        assertEquals("eng",detector.detectLang("What is the most beautiful city of Morocco"));
    }
    @Test
    void arabicDetector() {
        assertEquals("ara",detector.detectLang("مرحبا كيف حالك الليلة؟"));
        assertEquals("ara",detector.detectLang("أهلا. أنا جائع وأريد أن آكل؟"));
        assertEquals("ara",detector.detectLang("أهلا. ما هي اجمل مدينة في المغرب؟"));
    }
}