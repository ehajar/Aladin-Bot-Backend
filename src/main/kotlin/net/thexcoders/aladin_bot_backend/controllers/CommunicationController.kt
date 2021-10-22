package net.thexcoders.aladin_bot_backend.controllers

import net.thexcoders.aladin_bot_backend.converters.codeToLangConverter
import net.thexcoders.aladin_bot_backend.converters.langToCodeConverter
import net.thexcoders.aladin_bot_backend.nlp_models.ModelsParent
import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetector
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("API/coms")
@CrossOrigin("*")
class CommunicationController {

    @PostMapping("/communicate")
    fun communicate(@RequestBody data: ModelMap): ModelMap {
        val message = data.getAttribute("message") as String;
        val langCode = data.getAttribute("langCode") as Int;
        val languageStr = codeToLangConverter(langCode);

        var model = ModelsParent(languageStr);

        return model.initToModelMap(message);
    }


}