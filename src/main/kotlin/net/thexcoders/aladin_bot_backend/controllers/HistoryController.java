package net.thexcoders.aladin_bot_backend.controllers;

import net.thexcoders.aladin_bot_backend.models.History;
import net.thexcoders.aladin_bot_backend.repositories.HistoryRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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


}
