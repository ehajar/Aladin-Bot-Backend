package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.Categorizer;
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.CategorizerImpl;
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.CategoryResultImpl;
import net.thexcoders.aladin_bot_backend.nlp_models.sentence.SentenceDetector;
import net.thexcoders.aladin_bot_backend.nlp_models.sentence.SentenceDetectorImplEng;
import net.thexcoders.aladin_bot_backend.nlp_models.sentence.SentenceDetectorImplFra;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.Tokenizer;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.TokenizerImplEng;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.TokenizerImplFra;

import java.util.ArrayList;
import java.util.List;

public class ModelsOrchestrerImpl implements ModelsOrchestrer {

    private final CategorizerImpl.Language lang;

    public ModelsOrchestrerImpl(CategorizerImpl.Language lang) {
        this.lang = lang;
    }

    public List<CategorizerImpl.CategoryResult> getCategories(String[] input) {
        if (lang.equals(CategorizerImpl.Language.EN)) return initEng(input);
        if (lang.equals(CategorizerImpl.Language.FR)) return initFra(input);
        return new ArrayList<>();
    }

    public String[] toSentences(String input) {
        if (lang.equals(CategorizerImpl.Language.EN)) {
            SentenceDetector sentenceDetector = new SentenceDetectorImplEng();
            return sentenceDetector.toSentences(input.toLowerCase());
        }
        if (lang.equals(CategorizerImpl.Language.FR)) {
            SentenceDetectorImplFra sentenceDetector = new SentenceDetectorImplFra();
            return sentenceDetector.toSentences(input.toLowerCase());
        }
        return new String[]{};
    }

    private ArrayList<CategorizerImpl.CategoryResult> initEng(String[] sentences) {
        ArrayList<CategorizerImpl.CategoryResult> res = new ArrayList<>();

        Tokenizer mTokenizer = new TokenizerImplEng();
        CategorizerImpl categorizer = CategorizerImpl.getInstance();

        for (String sentence : sentences) {
            CategorizerImpl.CategoryResult catResult;
            if (sentence.toLowerCase().contains("hey")
                    || sentence.toLowerCase().contains("hello")
                    || sentence.toLowerCase().contains("hi")) {
                catResult = new CategoryResultImpl("greeting", 1, sentence);
            } else {
                String[] tokens = mTokenizer.tokenize(sentence);
                catResult = categorizer.getCategory(tokens, CategorizerImpl.Language.EN, sentence);
            }
            if (!catResult.isIn(res)) res.add(catResult);
        }
        return res;
    }

    private ArrayList<CategorizerImpl.CategoryResult> initFra(String[] sentences) {
        ArrayList<CategorizerImpl.CategoryResult> res = new ArrayList<>();
        Tokenizer mTokenizer = new TokenizerImplFra();
        CategorizerImpl categorizer = CategorizerImpl.getInstance();

        for (String sentence : sentences) {
            System.err.println("\n" + sentence);
            String[] tokens = mTokenizer.tokenize(sentence);
            res.add(categorizer.getCategory(tokens, Categorizer.Language.FR, sentence));
        }
        return res;
    }

}
