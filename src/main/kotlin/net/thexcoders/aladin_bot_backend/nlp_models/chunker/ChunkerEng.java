package net.thexcoders.aladin_bot_backend.nlp_models.chunker;

import net.thexcoders.aladin_bot_backend.nlp_models.NLPModel;
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.Categorizer;
import net.thexcoders.aladin_bot_backend.nlp_models.pos_tagger.POSTaggerEng;
import net.thexcoders.aladin_bot_backend.nlp_models.sentence.SentenceDetectorEng;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.TokenizerEng;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class ChunkerEng extends NLPModel {


    ChunkerModel model;

    public ChunkerEng() {
        this.fileName = "en-chunker.bin";
        init();
    }

    private void init() {
        File file = generateFile();
        try {
            model = new ChunkerModel(file);
        } catch (IOException ignored) {
        }
    }


    public String[] chunk(String[] data, String[] pos) {
        ChunkerME chunkerME = new ChunkerME(model);
        return chunkerME.chunk(data, pos);
    }


}
