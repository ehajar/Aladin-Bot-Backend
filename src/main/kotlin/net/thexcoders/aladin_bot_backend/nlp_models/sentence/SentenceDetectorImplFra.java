package net.thexcoders.aladin_bot_backend.nlp_models.sentence;

import net.thexcoders.aladin_bot_backend.nlp_models.NLPModel;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetectorImplFra extends NLPModel implements SentenceDetector {
    SentenceModel model;

    public SentenceDetectorImplFra() {
    }

    public String[] toSentences(String message){
        return message.split("[.?!]");
    }
}
