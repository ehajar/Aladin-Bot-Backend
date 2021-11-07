package net.thexcoders.aladin_bot_backend.models;


import jdk.jfr.Timestamp;
import lombok.Setter;
import net.thexcoders.aladin_bot_backend.helper_classes.HistoryState;
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.CategorizerImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.ui.ModelMap;

import java.util.Date;

/**
 * History Model to store the History of the transactions between the client and the bot to be used later to feed and improve the accuracy of the training Model.
 * @see CategorizerImpl.CategoryResult
 * @see HistoryState
 * @see Date
 * @see ModelMap
 * * */
@Document(collection = "History")
@Setter
public class History {

    // final static values
    @Transient
    public static final String FR = "fr";
    @Transient
    public static final String EN = "en";


    @Id
    private String id;
    private String input;
    private String lang;
    private CategorizerImpl.CategoryResult output;
    private String state = HistoryState.WAITING.stateValue;
    @Timestamp
    private Date created_at = new Date();


    /**
     * History Model Constructor
     * @param input a String representing the user Input
     * @param output the CategoryResult instance that represents the output of the bot
     * @param lang A string representing the language in which the communication has been done.
     * */
    public History(String input, CategorizerImpl.CategoryResult output, String lang) {
        this.input = input;
        this.output = output;
        this.lang = lang;
    }

    /**
     * Generating a ModelMap instance using the instance attributes.
     * */
    public ModelMap toModelmap() {
        ModelMap res = new ModelMap();
        res.addAttribute("id", id);
        res.addAttribute("input", input);
        res.addAttribute("lang", lang);
        res.addAttribute("state", state);
        res.addAttribute("output", output.toModelMap());

        return res;
    }

    /**
     * Change the state of the instance of History based in the state entered as a Parameter
     * @param state Integer referring to the target state to change the value to
     *
     * @see HistoryState
     * */
    public void changeState(int state) {
        switch (state) {
            case 0:
                this.state = HistoryState.VALIDATED.stateValue;
                break;
            case 1:
                this.state = HistoryState.REJECTED.stateValue;
                break;
            default:
                this.state = HistoryState.WAITING.stateValue;
        }
    }
}
