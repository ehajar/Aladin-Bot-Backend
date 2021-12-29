package net.thexcoders.aladin_bot_backend.nlp_models.language;


import net.thexcoders.aladin_bot_backend.nlp_models.NLPModel;
import opennlp.tools.langdetect.Language;
import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;

import java.io.File;
import java.io.IOException;

/**
 * This class is used to determine the language in which the user is communicating.
 * The language will be used for determining the language of the bot response.
 */
public class LangDetectorImpl extends NLPModel implements LangDetector {

    private LanguageDetectorModel model;

    public LangDetectorImpl() {
        fileName = "langDetect.bin";
        init();
    }

    /**
     * this method prepares the {@link LanguageDetectorModel} that will be used to get the language.
     */
    private void init() {
        File file = generateFile();
        try {
            model = new LanguageDetectorModel(file);
        } catch (IOException ignored) {}
    }

    /**
     * detects the language of the message and prints into the logs the value and the precision of the detection.
     *
     * @param input a String value representing the input of the user
     * @return String value of the language detected.
     */
    public String detectLang(String input) {
        LanguageDetector lang = new LanguageDetectorME(model);
        Language bestLanguage = lang.predictLanguage(input);
        System.err.println("Language :" +
                bestLanguage.getLang() +
                "\tprecision: " +
                bestLanguage.getConfidence());
        return bestLanguage.getLang();
    }


}
