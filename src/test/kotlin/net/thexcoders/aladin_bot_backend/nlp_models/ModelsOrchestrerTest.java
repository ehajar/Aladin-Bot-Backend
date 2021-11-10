package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.converters.CategoryConverter;
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.Categorizer;
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.CategorizerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelsOrchestrerTest {
    static ModelsOrchestrerImpl modelsOrchestrer;

    @BeforeEach
    void setUp() {
        modelsOrchestrer = new ModelsOrchestrerImpl(CategorizerImpl.Language.EN);
    }

    @Test
    void greetingsTest() {
        List<CategorizerImpl.CategoryResult> results = modelsOrchestrer.getCategories(new String[]{"hello"});
        assertEquals(CategoryConverter.GREETINGS, results.get(0).getCatCode());

        results = modelsOrchestrer.getCategories(new String[]{"Hey"});
        assertEquals(CategoryConverter.GREETINGS, results.get(0).getCatCode());

        results = modelsOrchestrer.getCategories(new String[]{"Hi"});
        assertEquals(CategoryConverter.GREETINGS, results.get(0).getCatCode());
    }

    @Test
    void bestPlaceTest() {
        List<CategorizerImpl.CategoryResult> results = modelsOrchestrer.getCategories(new String[]{"what's the best city to visit?"});
        assertEquals(CategoryConverter.BEST_PLACE, results.get(0).getCatCode());

        results = modelsOrchestrer.getCategories(new String[]{"what is the nicest place to see?"});
        assertEquals(CategoryConverter.BEST_PLACE, results.get(0).getCatCode());

    }

    @Test
    void bestFoodTest() {
        List<CategorizerImpl.CategoryResult> results = modelsOrchestrer.getCategories(new String[]{"What's the best food in Moroooccco?"});
        assertEquals(CategoryConverter.BEST_FOOD, results.get(0).getCatCode());

        results = modelsOrchestrer.getCategories(new String[]{"What's the best fooood in Moroooccco?"});
        assertEquals(CategoryConverter.BEST_FOOD, results.get(0).getCatCode());

        results = modelsOrchestrer.getCategories(new String[]{"any good food in Morocco?"});
        assertEquals(CategoryConverter.BEST_FOOD, results.get(0).getCatCode());


    }

}