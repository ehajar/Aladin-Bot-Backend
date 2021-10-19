package net.thexcoders.aladin_bot_backend.nlp_models.pos_tagger;

import net.thexcoders.aladin_bot_backend.nlp_models.NLPModel;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.io.File;

public class POSTaggerEng extends NLPModel {

    POSModel model;

    public POSTaggerEng(){
        this.fileName = "en-pos-maxent.bin";
        init();
    }
    private void init(){
        File file = generateFile();
        model = new POSModelLoader().load(file);
    }

    public String[] tag(String[] tokens){
        return new POSTaggerME(model).tag(tokens);
    }
}
