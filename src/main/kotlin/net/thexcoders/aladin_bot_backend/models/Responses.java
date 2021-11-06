package net.thexcoders.aladin_bot_backend.models;


import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Responses")
public class Responses {

    @Id
    private String name;
    public List<String> values = new ArrayList<>();

    public Responses(String name) {
        this.name = name;
    }

    public Responses add(String str) {
        this.values.add(str);
        return this;
    }

    public ModelMap toModelmap() {
        ModelMap res = new ModelMap();
        res.addAttribute("id", name);
        res.addAttribute("values", values);

        return res;
    }
}
