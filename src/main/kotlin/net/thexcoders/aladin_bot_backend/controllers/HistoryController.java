package net.thexcoders.aladin_bot_backend.controllers;

import net.thexcoders.aladin_bot_backend.models.History;
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.CategorizerImpl;
import net.thexcoders.aladin_bot_backend.repositories.HistoryRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * History Controller:
 * represents all the history of the communication between the user and the chatbot logged in the database.
 * This controller mainly communicates with the admin interface to get the data such as the history list and the training model to be altered.
 */
@RestController
@RequestMapping("API/history")
@CrossOrigin("*")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    /**
     * Gets all the history from the database to be displayed in the admin interface.
     * @return Array of ModelMap
     * the returned values are of type Array of modelMap and each element has the following strucutre:
     *  <div>{ </div>
     * <div style="margin-left:10px">id : String, </div>
     * <div style="margin-left:10px"> input : String,  </div>
     * <div style="margin-left:10px">lang : String,  </div>
     * <div style="margin-left:10px">state : String,  </div>
     * <div style="margin-left:10px">output : { </div>
     *         <div style="margin-left:20px">probability : double,</div>
     *             <div style="margin-left:20px">catCode : int, </div>
     *                 <div style="margin-left:20px"> category : String </div>
     *                     <div style="margin-left:10px"> } </div>
     *
     *     <div>}</div>
     * The model is {@link History History} and it uses {@link CategorizerImpl.CategoryResult CategoryResult}
     * */
    @GetMapping("/all")
    public List<ModelMap> getAll() {
        List<History> values = historyRepository.findAll();
        List<ModelMap> res = new ArrayList<>();
        for (History history : values) {
            res.add(history.toModelmap());
        }
        return res;
    }

    /**
     * Gets the file based on the language selected and returns it to be downloaded by the user
     * @param lang String the language to be used to retrieve the training model.
     * @return {@link ResponseEntity Response Entity of FileSystem Ressource} a response with a text file Blob
     *
     * @see ResponseEntity
     * @see FileSystemResource
     * @see HttpHeaders
     * @see HttpStatus
     * */
    @GetMapping("/download/{lang}")
    public ResponseEntity<FileSystemResource> download(@PathVariable("lang") String lang) {
        String path = "src/main/resources/categories_" + lang + ".txt";
        File file = new File(path);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(new MediaType(MediaType.TEXT_PLAIN));
        respHeaders.setContentLength(file.length());
        respHeaders.setContentDispositionFormData("attachment", "categories_" + lang + ".txt");

        return new ResponseEntity<>(new FileSystemResource(file), respHeaders, HttpStatus.OK);
    }

    /**
     * Uploads the file content and saves it to the model feeder based on the language selected and returns if the file has been updated or not
     * @param lang String the language to be used to retrieve the training model.
     * @param body ModelMap with the text attribute with the content of the updated file.
     * @return boolean to make sure that the file has been correctly uploaded.
     *
     * @see FileWriter
     * */


    @PostMapping("/upload/{lang}")
    public boolean upload(@PathVariable("lang") String lang, @RequestBody ModelMap body) {
        String path = "src/main/resources/categories_" + lang + ".txt";
        String content = (String) body.getAttribute("text");
        try {
            FileWriter file = new FileWriter(path);
            file.write(content);
            file.close();
            CategorizerImpl.modelChanged();
            return true;
        } catch (IOException e) {
            return false;
        }

    }


    /**
     * Gets the file based on the language selected and returns it to be downloaded by the user
     * @param historyElem ModelMap containing the state to be changed to and the id of the element to change.
     *
     * @see Optional
     * @see History
     * @see HttpHeaders
     * @see HistoryRepository
     * */
    @PostMapping("/state")
    public void changeState(@RequestBody ModelMap historyElem) {
        Integer state = (Integer) historyElem.getAttribute("state");
        String id = (String) historyElem.getAttribute("id");

        Optional<History> history = historyRepository.findById(id != null ? id : "");

        history.ifPresent(value -> {
            value.changeState(state != null ? state : 1);
            historyRepository.save(value);
        });

    }


}
