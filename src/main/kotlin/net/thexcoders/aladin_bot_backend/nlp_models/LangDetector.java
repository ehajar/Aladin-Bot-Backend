package net.thexcoders.aladin_bot_backend.nlp_models;

import java.io.File;

public abstract class LangDetector  {
    protected final String path = "src/main/resources/";
    protected String fileName;

    public boolean testFile(String fileName){
        File file = new File(path+fileName);
        return file.exists();
    }

    public abstract boolean init();

}
