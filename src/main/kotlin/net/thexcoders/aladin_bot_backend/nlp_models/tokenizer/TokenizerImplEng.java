package net.thexcoders.aladin_bot_backend.nlp_models.tokenizer;

import net.thexcoders.aladin_bot_backend.nlp_models.NLPModel;
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.CategorizerImpl;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.File;
import java.io.IOException;

/**
 * The Tokenizer splits each sentence into an array of tokens that will be used to get the associated Category
 * It is highly linked to {@link CategorizerImpl Categorizer}
 * */
public class TokenizerImplEng extends NLPModel implements TokenizerEng {
    TokenizerModel model ;

    public TokenizerImplEng(){
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
