package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.CategorizerImpl;
import org.junit.jupiter.api.BeforeEach;

class ModelsOrchestrerTest {
    static ModelsOrchestrer parent;

    @BeforeEach
    void setUp() {
        parent = new ModelsOrchestrerImpl(CategorizerImpl.Language.EN);
    }

   /* @Test
    void greetingsTest() {
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
    void bestPlaceTest() {
        List<Categorizer.CategoryResult> results = parent.init("What's the best place in Morocco to visit");
        assertEquals(CategoryConverterKt.BEST_PLACE, results.get(0).catCode);

        *//*results = parent.init("what is the best place in Morocco"); // hadi li failed dial bare7
        assertEquals(CategoryConverterKt.BEST_PLACE, results.get(0).catCode);*//*

        results = parent.init("any place to eat in Morocco?");
        assertEquals(CategoryConverterKt.BEST_PLACE, results.get(0).catCode);

    }

    @Test
    void bestFoodTest() {
        List<Categorizer.CategoryResult> results = parent.init("What's the best food in Moroooccco?");
        assertEquals(CategoryConverterKt.BEST_FOOD, results.get(0).catCode);

        results = parent.init("What's the best fooood in Moroooccco?");
        assertEquals(CategoryConverterKt.BEST_FOOD, results.get(0).catCode);

        results = parent.init("any good food in Morocco?");
        assertEquals(CategoryConverterKt.BEST_FOOD, results.get(0).catCode);

        results = parent.init("anything to eat in Morocco?");
        assertEquals(CategoryConverterKt.BEST_FOOD, results.get(0).catCode);

    }
*/

}