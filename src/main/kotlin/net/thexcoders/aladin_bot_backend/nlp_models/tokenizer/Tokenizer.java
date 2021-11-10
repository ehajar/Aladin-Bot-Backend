package net.thexcoders.aladin_bot_backend.nlp_models.tokenizer;

import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.CategorizerImpl;

/**
 * The Tokenizer splits each sentence into an array of tokens that will be used to get the associated Category
 * It is highly linked to {@link CategorizerImpl Categorizer}
 */
public interface Tokenizer {

    String[] tokenize(String input);

}
