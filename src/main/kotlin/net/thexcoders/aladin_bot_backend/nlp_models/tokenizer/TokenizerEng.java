package net.thexcoders.aladin_bot_backend.nlp_models.tokenizer;

import net.thexcoders.aladin_bot_backend.nlp_models.NLPModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.File;
import java.io.IOException;

public class TokenizerEng extends NLPModel {
    TokenizerModel model ;

    public TokenizerEng(){
        this.fileName = "en-token.bin";
        init();
    }
    private void init(){
        File file = generateFile();
        try {
            model = new TokenizerModel(file);
        } catch (IOException ignored) {}
    }

    public String[] tokenize(String input){
        return new TokenizerME(model).tokenize(input);
    }

}
