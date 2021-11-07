package net.thexcoders.aladin_bot_backend.nlp_models.sentence;

import net.thexcoders.aladin_bot_backend.nlp_models.NLPModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.File;
import java.io.IOException;

public class SentenceDetectorImplFra extends NLPModel implements SentenceDetectorEng {
    SentenceModel model;

    public SentenceDetectorImplFra() {
    }

    public String[] toSentences(String message){
        return message.split("[.?!]");
    }
}
