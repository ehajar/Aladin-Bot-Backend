package net.thexcoders.aladin_bot_backend.nlp_models;

import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.Categorizer;
import net.thexcoders.aladin_bot_backend.nlp_models.sentence.SentenceDetectorEng;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.TokenizerEng;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

public class ModelsParent {
    Categorizer.Language lang;

    public ModelsParent(Categorizer.Language lang) {
        this.lang = lang;
    }

    public List<Categorizer.CategoryResult> init(String input) {
        if (lang.equals(Categorizer.Language.EN)) return initEng(input);
        if (lang.equals(Categorizer.Language.FR)) return initFra(input);
        return new ArrayList<>();
    }

    public ModelMap initToModelMap(String input) {
        List<Categorizer.CategoryResult> resList = init(input);
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

    private ArrayList<Categorizer.CategoryResult> initEng(String input) {
        ArrayList<Categorizer.CategoryResult> res = new ArrayList<>();
        TokenizerEng mTokenizer = new TokenizerEng();
        Categorizer categorizer = Categorizer.getInstance();
        SentenceDetectorEng sentenceDetector = new SentenceDetectorEng();

        String[] sentences = sentenceDetector.toSentences(input.toLowerCase());
        for (String sentence : sentences) {
            if(sentence.toLowerCase().contains("hey")
            || sentence.toLowerCase().contains("hello")
            || sentence.toLowerCase().contains("hi")) {
                res.add(new Categorizer.CategoryResult("greeting",1));
            }
            String[] tokens = mTokenizer.tokenize(sentence);
            Categorizer.CategoryResult catResult = categorizer.getCategory(tokens,Categorizer.Language.EN);
            if (!catResult.isIn(res)) res.add(catResult);
        }
        return res;
    }

    // TODO : setup French models and french training models
    private ArrayList<Categorizer.CategoryResult> initFra(String input) {
        ArrayList<Categorizer.CategoryResult> res = new ArrayList<>();
        TokenizerEng mTokenizer = new TokenizerEng();
        Categorizer categorizer = Categorizer.getInstance();
        SentenceDetectorEng sentenceDetector = new SentenceDetectorEng();

        String[] sentences = sentenceDetector.toSentences(input.toLowerCase());
        for (String sentence : sentences) {
            System.err.println("\n" + sentence);
            String[] tokens = mTokenizer.tokenize(sentence);
            res.add(categorizer.getCategory(tokens,Categorizer.Language.FR));
        }
        return res;
    }


    public enum Language{
        EN,FR;
    }
}
