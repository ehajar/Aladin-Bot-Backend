package net.thexcoders.aladin_bot_backend.converters;

import org.junit.jupiter.api.Test;

import static net.thexcoders.aladin_bot_backend.converters.CategoryConverter.*;
import static net.thexcoders.aladin_bot_backend.converters.CategoryConverter.categoryConverter;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryConverterTest {


    @Test
    void testCatConverter() {
        assertEquals(GREETINGS, categoryConverter("greeting"));
        assertEquals(CONV_END, categoryConverter("conv-end"));
        assertEquals(EMERGENCY, categoryConverter("emergency"));
        assertEquals(CREATE_DATA, categoryConverter("create-data"));
        assertEquals(TIME_DATA, categoryConverter("time-data"));
        assertEquals(BEST_PLACE, categoryConverter("best-place"));
        assertEquals(BEST_FOOD, categoryConverter("best-food"));
        assertEquals(UNKNOWN_CAT, categoryConverter("randomValue"));
    }

}
