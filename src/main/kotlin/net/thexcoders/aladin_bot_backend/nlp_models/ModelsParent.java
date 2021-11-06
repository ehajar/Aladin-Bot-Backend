package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.Categorizer;
import net.thexcoders.aladin_bot_backend.nlp_models.sentence.SentenceDetectorEng;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.TokenizerEng;
import net.thexcoders.aladin_bot_backend.repositories.HistoryRepository;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

public class ModelsParent {

    private HistoryRepository databaseHelper;

    Categorizer.Language lang;

    public ModelsParent(Categorizer.Language lang) {
        this.lang = lang;
    }

    public List<Categorizer.CategoryResult> getCategories(String[] input) {
        if (lang.equals(Categorizer.Language.EN)) return initEng(input);
        if (lang.equals(Categorizer.Language.FR)) return initFra(input);
        return new ArrayList<>();
    }

    public String[] toSentences(String input) {
        if (lang.equals(Categorizer.Language.EN)) {
            SentenceDetectorEng sentenceDetector = new SentenceDetectorEng();
            return sentenceDetector.toSentences(input.toLowerCase());
        }
        if (lang.equals(Categorizer.Language.FR)) {
            SentenceDetectorEng sentenceDetector = new SentenceDetectorEng();
            return sentenceDetector.toSentences(input.toLowerCase());
        }
        ;
        return new String[]{};
    }

    public ModelMap initToModelMap(String input, HistoryRepository databaseHelper) {
        this.databaseHelper = databaseHelper;
        List<Categorizer.CategoryResult> resList = getCategories(toSentences(input));
        ArrayList<ModelMap> mapList = new ArrayList<>();
        ModelMap res = new ModelMap();
        for (Categorizer.CategoryResult cat : resList) {
            ModelMap temp = new ModelMap();
            temp.addAttribute("catCode", cat.catCode);
            temp.addAttribute("cat", cat.category);
            mapList.add(temp);
        }
        res.addAttribute("data", mapList);
        return res;
    }

    private ArrayList<Categorizer.CategoryResult> initEng(String[] sentences) {
        ArrayList<Categorizer.CategoryResult> res = new ArrayList<>();

        TokenizerEng mTokenizer = new TokenizerEng();
        Categorizer categorizer = Categorizer.getInstance();

        for (String sentence : sentences) {
            Categorizer.CategoryResult catResult;
            if (sentence.toLowerCase().contains("hey")
                    || sentence.toLowerCase().contains("hello")
                    || sentence.toLowerCase().contains("hi")) {
                catResult = new Categorizer.CategoryResult("greeting", 1, sentence);
            } else {
                String[] tokens = mTokenizer.tokenize(sentence);
                catResult = categorizer.getCategory(tokens, Categorizer.Language.EN, sentence);
            }
            if (!catResult.isIn(res)) res.add(catResult);
        }
        return res;
    }

    // TODO : setup French models and french training models
    private ArrayList<Categorizer.CategoryResult> initFra(String[] sentences) {
        ArrayList<Categorizer.CategoryResult> res = new ArrayList<>();
        TokenizerEng mTokenizer = new TokenizerEng();
        Categorizer categorizer = Categorizer.getInstance();

        for (String sentence : sentences) {
            System.err.println("\n" + sentence);
            String[] tokens = mTokenizer.tokenize(sentence);
            res.add(categorizer.getCategory(tokens, Categorizer.Language.FR, sentence));
        }
        return res;
    }


    public enum Language {
        EN, FR;
    }
}
