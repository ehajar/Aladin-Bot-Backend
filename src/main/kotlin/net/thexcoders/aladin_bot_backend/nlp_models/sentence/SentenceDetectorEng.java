package net.thexcoders.aladin_bot_backend.nlp_models.sentence;

/**
 * This class is used to split the user input into the sentences
 */
public interface SentenceDetectorEng {

    /**
     * @param message a String with the value of the user input
     * @return Array of Strings containing all the sentences retrieved form the user Input.
     */
    String[] toSentences(String message);
}
