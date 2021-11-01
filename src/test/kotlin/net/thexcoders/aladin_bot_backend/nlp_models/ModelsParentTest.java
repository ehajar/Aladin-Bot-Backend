package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.converters.CategoryConverterKt;
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.Categorizer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelsParentTest {
    static ModelsParent parent;

    @BeforeEach
    void setUp() {
        parent = new ModelsParent(Categorizer.Language.EN);
    }

    @Test
    void greetingsTest(){
        List<Categorizer.CategoryResult> results = parent.init("hello");
        assertEquals(CategoryConverterKt.GREETINGS, results.get(0).catCode);

        results = parent.init("Hey");
        assertEquals(CategoryConverterKt.GREETINGS, results.get(0).catCode);

        results = parent.init("Hi");
        assertEquals(CategoryConverterKt.GREETINGS, results.get(0).catCode);

        results = parent.init("How are you");
        assertEquals(CategoryConverterKt.GREETINGS, results.get(0).catCode);
    }

    @Test
    void bestPlaceTest(){
        List<Categorizer.CategoryResult> results = parent.init("What's the best place in Morocco to visit");
        assertEquals(CategoryConverterKt.BEST_PLACE, results.get(0).catCode);

        results = parent.init("what is the best place in Morocco");
        assertEquals(CategoryConverterKt.BEST_PLACE, results.get(0).catCode);

    }



}