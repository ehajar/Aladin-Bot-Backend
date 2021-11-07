package net.thexcoders.aladin_bot_backend.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.ui.ModelMap;

import java.util.*;

/**
 * Responses Model to store the Responses that will be sent to the user.
 *
 * @see ModelMap
 * @see Map
 * @see HashMap
 * @see List
 */
@Document(collection = "Responses")
public class Responses {
    @Id
    private String name;
    private String lang;
    public Map<String, List<String>> values = new HashMap<>();


    /**
     * Response Constructor that takes two parameters
     *
     * @param name String representing the response category name of the result
     * @param lang String representing the key of the {@link HashMap HashMap} used to store the responses : {@link #values }
     */
    public Responses(String name, String lang) {
        this.name = name;
        this.lang = lang;
    }

    /**
     * adding a new element to the hashMap using the language entered as a parameter to the constructor
     *
     * @param str a String representing the value to insert to the response category name
     * @return Responses returns the instance called to be used to call the function add once again.
     */
    public Responses add(String str) {
        List<String> list = this.values.get(lang);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(str);
        this.values.put(lang, list);
        return this;
    }

    public Responses setLang(String lang) {
        this.lang = lang;
        return this;
    }
}
