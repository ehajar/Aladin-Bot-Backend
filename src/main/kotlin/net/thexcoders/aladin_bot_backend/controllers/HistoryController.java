package net.thexcoders.aladin_bot_backend.controllers;

import net.thexcoders.aladin_bot_backend.models.History;
import net.thexcoders.aladin_bot_backend.repositories.HistoryRepository;

import java.io.File;
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

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("API/history")
@CrossOrigin("*")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;


    @GetMapping("/all")
    public List<ModelMap> getAll() {
        List<History> values = historyRepository.findAll();
        List<ModelMap> res = new ArrayList<>();
        for (History history : values) {
            res.add(history.toModelmap());
        }
        return res;
    }

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

    @PostMapping("/state")
    public void changeState(@RequestBody ModelMap s) {
        Integer state = (Integer) s.getAttribute("state");
        String id = (String) s.getAttribute("id");

        Optional<History> history = historyRepository.findById(id != null ? id : "");

        history.ifPresent(value -> {
            value.changeState(state != null ? state : 1);
            historyRepository.save(value);
        });

    }


}
