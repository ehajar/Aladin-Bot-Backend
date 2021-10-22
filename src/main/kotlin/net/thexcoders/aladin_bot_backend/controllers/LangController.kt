package net.thexcoders.aladin_bot_backend.controllers

import net.thexcoders.aladin_bot_backend.converters.codeToLangConverter
import net.thexcoders.aladin_bot_backend.converters.langToCodeConverter
import net.thexcoders.aladin_bot_backend.nlp_models.ModelsParent
import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetector
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("API/language")
@CrossOrigin("*")
class LangController {


    @PostMapping("/get")
    fun detectLang(@RequestBody data: ModelMap): ModelMap {
        // get user Input
        val res = ModelMap();
        val msg: String = data.getAttribute("message") as String;

        // detect Language
        val detector = LangDetector();
        val lang = detector.detectLang(msg)

        // convert to Code
        val langCode = langToCodeConverter(lang)
        res.addAttribute("lang", lang);
        res.addAttribute("langCode", langCode);
        return res;
    }



}