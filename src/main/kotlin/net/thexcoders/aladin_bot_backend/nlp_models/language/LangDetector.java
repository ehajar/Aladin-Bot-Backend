package net.thexcoders.aladin_bot_backend.nlp_models.language;

/**
 * This class is used to determine the language in which the user is communicating.
 * The language will be used for determining the language of the bot response.
 */
public interface LangDetector {


    /**
     * detects the language of the message and prints into the logs the value and the precision of the detection.
     *
     * @param input a String value representing the input of the user
     * @return String value of the language detected.
     */
    String detectLang(String input);


}
