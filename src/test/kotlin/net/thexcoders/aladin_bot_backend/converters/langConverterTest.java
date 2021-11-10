package net.thexcoders.aladin_bot_backend.converters;

import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.Categorizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static net.thexcoders.aladin_bot_backend.converters.LangConverter.*;

class langConverterTest {


    @Test
    void testLangCodeConverter() {
        assertEquals(FR, langToCodeConverter("fra"));
        assertEquals(AR, langToCodeConverter("ara"));
        assertEquals(EN, langToCodeConverter("eng"));
        assertEquals(UNKNOWN, langToCodeConverter("ger"));
    }

    @Test
    void testCodeLangConverter() {
        assertEquals(Categorizer.Language.FR, codeToLangConverter(0));
        assertEquals(Categorizer.Language.FR, codeToLangConverter(FR));
        assertEquals(Categorizer.Language.EN, codeToLangConverter(1));
    }

}
