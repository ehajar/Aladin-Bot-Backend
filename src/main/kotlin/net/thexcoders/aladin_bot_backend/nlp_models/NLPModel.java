package net.thexcoders.aladin_bot_backend.nlp_models;

import java.io.File;
import java.io.IOException;

public abstract class NLPModel {
    protected final String path = "src/main/resources/";
    protected String fileName;

    public boolean testFile(String fileName){
        File file = new File(path+fileName);
        return file.exists();
    }

    public boolean fileExists(){
        return testFile(fileName);
    }

    public File generateFile(){
        return new File(path+fileName);
    }

}
