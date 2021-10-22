package net.thexcoders.aladin_bot_backend.nlp_models.categorizer;

import net.thexcoders.aladin_bot_backend.nlp_models.NLPModel;
import opennlp.tools.doccat.*;
import opennlp.tools.util.*;

import java.io.*;
import java.util.ArrayList;

import static net.thexcoders.aladin_bot_backend.converters.CategoryConverterKt.categoryConverter;

// Singleton class due to the train mfunction
public class Categorizer extends NLPModel {

    private DoccatModel model;
    private Language language;
    private static Categorizer instance;

    enum Language {
        EN("en"), FR("fr");
        String value;

        Language(String lang) {
            this.value = lang;
        }
    }


    private Categorizer(Language language) {
        this.language = language;
        fileName = "categories_" + language.value + ".txt";

        this.model = train();
    }

    public static Categorizer getInstance(Language language) {
        if (instance == null) {
            instance = new Categorizer(language);
        }
        return instance;
    }

    public static Categorizer getInstance() {
        if (instance == null) {
            instance = new Categorizer(Language.EN);
        }
        return instance;
    }

    public DoccatModel train() {
        try {
            InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File(path + fileName));
            ObjectStream<String> lineStream =
                    new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

            DoccatFactory factory = new DoccatFactory(new FeatureGenerator[]{new BagOfWordsFeatureGenerator()});
            TrainingParameters params = TrainingParameters.defaultParams();
            params.put(TrainingParameters.ITERATIONS_PARAM, "500");
            params.put(TrainingParameters.CUTOFF_PARAM, "0");


            model = DocumentCategorizerME.train(language.value,
                    sampleStream, params, factory);
            return model;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CategoryResult getCategory(String[] tokens) {
        DocumentCategorizerME categorizerME = new DocumentCategorizerME(model);
        double[] probabilites = categorizerME.categorize(tokens);
        String category = categorizerME.getBestCategory(probabilites);
        System.err.print(category + "\t");
        double probability = probabilites[categorizerME.getIndex(category)];
        System.err.println("probability " + probability);
        return new CategoryResult(category, probability);
    }

    public static class CategoryResult {
        public String category;
        public double probability;
        public int catCode;

        public CategoryResult(String category, double probability) {
            this.category = category;
            this.probability = probability;
            this.catCode = categoryConverter(category);
        }

        public boolean isEqual(CategoryResult res) {
            return this.catCode == res.catCode;
        }

        public boolean isIn(ArrayList<CategoryResult> list){
            for (CategoryResult res : list){
                if (isEqual(res)) return true;
            }
            return false;
        }
    }

}
