package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.CategorizerImpl;

import java.util.List;

/**
 * This class, as it's name indicates, will take care of the orchestration between the different models
 * It creates all the instances and calls the methods in the correct orders.
 */
public interface ModelsOrchestrer {

    /**
     * this method will get all the categories from the user inputs.
     *
     * @param input an array of Strings representing the sentences entered by the user
     * @return  array of Category Result representing the list of unique categories.
     */
    List<CategorizerImpl.CategoryResult> getCategories(String[] input);

    /**
     * this method will prepare the list of sentences based on the user's input
     * @param input String representing user's input
     * @return String[] representing an array of sentences.
     */
    String[] toSentences(String input);

}
