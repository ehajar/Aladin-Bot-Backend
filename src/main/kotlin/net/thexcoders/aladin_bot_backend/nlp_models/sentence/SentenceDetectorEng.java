package net.thexcoders.aladin_bot_backend.nlp_models.sentence;

import net.thexcoders.aladin_bot_backend.nlp_models.NLPModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.File;
import java.io.IOException;

public class SentenceDetectorEng extends NLPModel {
    SentenceModel model;

    public SentenceDetectorEng() {
        fileName = "en-sentence.bin";
        init();
    }

    private void init() {
        File file = new File(path + fileName);
        try {
            model = new SentenceModel(file);
        } catch (IOException ignored) {
        }
    }

    public String[] toSentences(String message){
        SentenceDetectorME sentenceDetectorME = new SentenceDetectorME(model);
        return sentenceDetectorME.sentDetect(message);
    }
}
