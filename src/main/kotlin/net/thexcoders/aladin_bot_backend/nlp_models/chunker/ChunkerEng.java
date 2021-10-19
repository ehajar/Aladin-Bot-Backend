package net.thexcoders.aladin_bot_backend.nlp_models.chunker;

import net.thexcoders.aladin_bot_backend.nlp_models.NLPModel;
import net.thexcoders.aladin_bot_backend.nlp_models.pos_tagger.POSTaggerEng;
import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.TokenizerEng;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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

    public static void main(String[] args) {
        ChunkerEng mChunker = new ChunkerEng();
        TokenizerEng mTokenizer = new TokenizerEng();
        POSTaggerEng mPOSTagger = new POSTaggerEng();

        String input = "Hello, How are you doing today";
        String[] tokens = mTokenizer.tokenize(input);

        String[] tags = mPOSTagger.tag(tokens);
        String[] chunks = mChunker.chunk(tokens, tags);
        System.err.println(Arrays.asList(tokens));
        System.err.println(Arrays.asList(tags));
        System.err.println(Arrays.asList(chunks));
    }


}
