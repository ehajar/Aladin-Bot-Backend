package net.thexcoders.aladin_bot_backend.nlp_models;


import net.thexcoders.aladin_bot_backend.exceptions.AladinException;
import opennlp.tools.langdetect.Language;
import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;

import java.io.File;
import java.io.IOException;

public class LangDetector extends NLPModel{

    private LanguageDetectorModel model;

    public LangDetector() {
        fileName = "langDetect.bin";
        init();
    }

    private boolean init() {
        File file = new File(path + fileName);
        try {
            model = new LanguageDetectorModel(file);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String detectLang(String input){
        LanguageDetector lang = new LanguageDetectorME(model);
        Language bestLanguage = lang.predictLanguage(input);
        return bestLanguage.getLang();
    }


}
