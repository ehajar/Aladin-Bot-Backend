package net.thexcoders.aladin_bot_backend.nlp_models.categorizer;

import net.thexcoders.aladin_bot_backend.nlp_models.tokenizer.Tokenizer;
import opennlp.tools.doccat.*;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;


/**
 * Categorizer class aims to recognize the category of the sentence based on the training Model used.
 * The data used to train the model is based on the categories_[lang].txt
 * The file is dynamic and the admin can download the file, edit and save it back to the ressources folder.
 */
public interface Categorizer {

    /**
     * enumeration class with two values {@link Language#EN} and {@link Language#FR}.
     * Used to pass the Language values in the application especially for the result.
     */
    public enum Language {
        EN("en"), FR("fr");
        public final String languageValue;

        Language(String lang) {
            this.languageValue = lang;
        }
    }

    /**
     * gets the instance of the class, if the instance is null it calls the constructor to create a new instance
     *
     * @return Categorizer the instance of the class.
     */
    static CategorizerImpl getInstance() {
        return null;
    }

    /**
     * This method takes a lot of time to execute since it prepares the training Model for the categorizer based on the
     * training Data provided in the {@link CategorizerImpl#CategorizerImpl()} constructor} For optimization purposes, we Have used a singleton
     *
     * @param lang a string value representing the language in which the model will be trained.
     *             It also references the file to be used to train the Model
     * @return instance of {@link DoccatModel} which is the trained model.
     * @see ObjectStream
     * @see DoccatModel
     * @see TrainingParameters
     * @see DoccatFactory
     * @see DocumentSampleStream
     * @see DocumentSample
     * @see DocumentCategorizerME
     * @see MarkableFileInputStreamFactory
     * @see PlainTextByLineStream
     */
    public DoccatModel train(String lang);


    /**
     * this method is used to get the category of the tokenized sentence using the {@link Tokenizer Tokenizer} class.
     *
     * @param tokens   an array of tokens retrieved from the tokenizer
     * @param lang     the {@link Language} in which the tokens are in
     * @param sentence the sentenced (a single sentence from the user's input) from which the tokens are retrieved.
     * @return an instance of {@link CategorizerImpl.CategoryResult} representing the result to be displayed to the user.
     */
    public CategorizerImpl.CategoryResult getCategory(String[] tokens, Language lang, String sentence);

}
