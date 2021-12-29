package net.thexcoders.aladin_bot_backend.converters;

import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.Categorizer;
import org.junit.jupiter.api.Test;

import static net.thexcoders.aladin_bot_backend.converters.LangConverter.*;
import static org.junit.Assert.assertEquals;


class langConverterTest {

    @Test
    void testValidLang() {
        assertEquals(FR, langToCodeConverter("fra"));
        assertEquals(AR, langToCodeConverter("ara"));
        assertEquals(EN, langToCodeConverter("eng"));
    }
    @Test
    void testInvalidLang() {
        assertEquals(UNKNOWN, langToCodeConverter("ger"));
        assertEquals(UNKNOWN, langToCodeConverter("ar"));
        assertEquals(UNKNOWN, langToCodeConverter("fr"));
        assertEquals(UNKNOWN, langToCodeConverter("en"));
    }

    @Test
    void testValidLanguageEnum() {
        assertEquals(Categorizer.Language.FR, codeToLangConverter(FR));
        assertEquals(Categorizer.Language.EN, codeToLangConverter(EN));
    }

    @Test
    void testInvalidLanguageEnum() {
        assertEquals(Categorizer.Language.EN, codeToLangConverter(5));
        assertEquals(Categorizer.Language.EN, codeToLangConverter(-1));
        assertEquals(Categorizer.Language.EN, codeToLangConverter(-34));
    }
}