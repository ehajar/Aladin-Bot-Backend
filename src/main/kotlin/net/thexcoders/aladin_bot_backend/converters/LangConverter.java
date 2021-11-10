package net.thexcoders.aladin_bot_backend.converters;

import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.Categorizer;

public class LangConverter {


    public static final int UNKNOWN = -1;
    public static final int FR = 0;
    public static final int EN = 1;
    public static final int AR = 2;


    /**
     * Converting the Language from String to Integer
     *
     * @param lang String representing the language
     * @return Integer Code of the language
     */
    public static int langToCodeConverter(String lang) {
        switch (lang) {
            case "eng":
                return EN;
            case "fra":
                return FR;
            case "ara":
                return AR;
            default:
                return UNKNOWN;
        }
    }


    /**
     * Converting the Language from code to {@link Categorizer.Language Categorizer.Language}
     *
     * @param code Integer representing the language code
     * @return Categorizer.Language Code of the language
     * @see Categorizer.Language
     */
    public static Categorizer.Language codeToLangConverter(int code) {
        switch (code) {
            case FR:
                return Categorizer.Language.FR;
            default:
                return Categorizer.Language.EN;
        }
    }
}
