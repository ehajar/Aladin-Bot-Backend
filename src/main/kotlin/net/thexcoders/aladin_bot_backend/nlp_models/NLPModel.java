package net.thexcoders.aladin_bot_backend.nlp_models;

import java.io.File;

public abstract class NLPModel {
    protected static final String PATH = "src/main/resources/";
    protected String fileName;

    public boolean testFile(String fileName){
        File file = new File(PATH +fileName);
        return file.exists();
    }

    public boolean fileExists(){
        return testFile(fileName);
    }

    public File generateFile(){
        return new File(PATH +fileName);
    }

}
