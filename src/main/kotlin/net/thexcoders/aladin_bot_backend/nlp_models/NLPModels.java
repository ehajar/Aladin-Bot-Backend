package net.thexcoders.aladin_bot_backend.nlp_models;


import opennlp.tools.langdetect.LanguageDetectorModel;

import java.io.File;
import java.io.IOException;

public class NLPModels extends LangDetector{

    LanguageDetectorModel model;

    public NLPModels(){
        fileName="langDetect.bin";
    }

    @Override
    public boolean init() {
         File file = new File(path+fileName);
        try {
            model = new LanguageDetectorModel(file);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


}
