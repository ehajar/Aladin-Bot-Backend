package net.thexcoders.aladin_bot_backend.nlp_models.categorizer;

import lombok.Setter;
import net.thexcoders.aladin_bot_backend.nlp_models.NLPModel;
import opennlp.tools.doccat.*;
import opennlp.tools.util.*;
import org.springframework.ui.ModelMap;

import java.io.*;
import java.util.List;

import static net.thexcoders.aladin_bot_backend.converters.CategoryConverter.categoryConverter;


public class CategorizerImpl extends NLPModel implements Categorizer {
    private static final double MIN_ACCEPTED_VALUE = 0.1429;

    private final DoccatModel enModel;
    private final DoccatModel frModel;
    private static CategorizerImpl instance;


    /**
     * Categorizer constructor that prepares the files needed to train the Model in the languages added to the
     * Language Enumeration Class.
     * <p>
     * This class is private due to the method {@link #train(String)}
     */
    protected CategorizerImpl() {
        fileName = "categories_en.txt";
        this.enModel = train(Language.EN.languageValue);
        fileName = "categories_fr.txt";
        this.frModel = train(Language.FR.languageValue);
    }

    public static CategorizerImpl getInstance() {
        if (instance == null) {
            instance = new CategorizerImpl();
        }
        return instance;
    }

    @Override
    public DoccatModel train(String lang) {
        try {
            InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File(PATH + "categories_" + lang + ".txt"));
            ObjectStream<String> lineStream =
                    new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

            DoccatFactory factory = new DoccatFactory(new FeatureGenerator[]{new BagOfWordsFeatureGenerator()});
            TrainingParameters params = TrainingParameters.defaultParams();
            params.put(TrainingParameters.ITERATIONS_PARAM, "500");
            params.put(TrainingParameters.CUTOFF_PARAM, "0");

            DoccatModel model;
            model = DocumentCategorizerME.train(lang,
                    sampleStream, params, factory);
            return model;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CategoryResult getCategory(String[] tokens, Language lang, String sentence) {
        // preparing the Instance
        DocumentCategorizerME categorizerME;
        if (lang.languageValue.equals(Language.EN.languageValue)) {
            categorizerME = new DocumentCategorizerME(enModel);
        } else {
            categorizerME = new DocumentCategorizerME(frModel);
        }

        // getting the best category based on the probabilities
        double[] probabilities = categorizerME.categorize(tokens);
        String category = categorizerME.getBestCategory(probabilities);
        double probability = probabilities[categorizerME.getIndex(category)];

        System.err.println(category + "\t" + "probability " + probability);

        // if probability is below the default value return Unknown
        if (probability < MIN_ACCEPTED_VALUE) return new CategoryResultImpl("Unknown", probability, sentence);
        return new CategoryResultImpl(category, probability, sentence);
    }

    /**
     * this inner class is used to store the result of the {@link #getCategory(String[], Language, String)} method
     * it stores the values to be stored or logged.
     */
    public static class CategoryResult {
        protected String category;
        protected double probability;
        protected int catCode;
        protected String input;

        public CategoryResult(String category, double probability, String input) {
            this.category = category;
            this.probability = probability;
            this.input = input;
            this.catCode = categoryConverter(category);
        }

        /**
         * this method is used to make sure that the output doesn't have duplicate {@link CategorizerImpl.CategoryResult}
         *
         * @param list a list of {@link CategorizerImpl.CategoryResult} representing the list of results obtained
         * @return boolean
         */
        public boolean isIn(List<CategorizerImpl.CategoryResult> list) {
            return false;
        }

        public ModelMap toModelMap() {
            return new ModelMap();
        }

        /**
         * this methods compares if two categories have the same code.
         * In this case, we will consider the user's request as duplicate if within the results the same category
         * is repeated more than once.
         *
         * @param res instance of {@link CategorizerImpl.CategoryResult} representing the target to compare to.
         * @return boolean
         */
        public boolean isEqual(CategorizerImpl.CategoryResult res) {
            return false;
        }


        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public double getProbability() {
            return probability;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }

        public int getCatCode() {
            return catCode;
        }

        public void setCatCode(int catCode) {
            this.catCode = catCode;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }
    }

}
