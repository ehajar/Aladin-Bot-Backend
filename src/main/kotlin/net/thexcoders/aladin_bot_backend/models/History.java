package net.thexcoders.aladin_bot_backend.models;


import jdk.jfr.Timestamp;
import net.thexcoders.aladin_bot_backend.helper_classes.HistoryState;
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.Categorizer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.ui.ModelMap;

import java.util.Date;

@Document(collection = "History")
public class History {
    public static int COUNTER = 0;

    @Id
    private String id;

    private String input;
    private String lang = "en";
    private Categorizer.CategoryResult output;
    private String state = HistoryState.WAITING.state;
    @Timestamp
    private Date created_at = new Date();


    public History(String input, Categorizer.CategoryResult output,String lang) {
        this.input = input;
        this.output = output;
        this.lang = lang;
    }

    public ModelMap toModelmap() {
        ModelMap res = new ModelMap();
        res.addAttribute("id", id);
        res.addAttribute("input", input);
        res.addAttribute("lang", lang);
        res.addAttribute("state", state);
        res.addAttribute("output", output.toModelMap());

        return res;
    }

    public void changeState(int s) {
        switch (s) {
            case 0:
                state = HistoryState.VALIDATED.state;
                break;
            case 1:
                state = HistoryState.REJECTED.state;
                break;
            case 2:
                state = HistoryState.WAITING.state;
                break;
        }
    }
}
