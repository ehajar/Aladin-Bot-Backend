package net.thexcoders.aladin_bot_backend.controllers

import net.thexcoders.aladin_bot_backend.converters.codeToLangConverter
import net.thexcoders.aladin_bot_backend.nlp_models.ModelsParent
import net.thexcoders.aladin_bot_backend.repositories.HistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("API/coms")
@CrossOrigin("*")
class CommunicationController {

    @Autowired
    private lateinit var databaseHelper: HistoryRepository

    @PostMapping("/communicate")
    fun communicate(@RequestBody data: ModelMap): ModelMap {
        // input params
        val message = data.getAttribute("message") as String;
        val langCode = data.getAttribute("langCode") as Int;

        // instantiate Classes
        val languageStr = codeToLangConverter(langCode)
        val model = ModelsParent(languageStr)

        // processing the data;
        val res =  model.initToModelMap(message,databaseHelper)
        return res;
    }

}