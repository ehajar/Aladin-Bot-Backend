package net.thexcoders.aladin_bot_backend.controllers

import net.thexcoders.aladin_bot_backend.converters.converter
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
        var res = ModelMap();
        val msg: String = data.getAttribute("message") as String;

        // detect Language
        var detector = LangDetector();
        val lang = detector.detectLang(msg)

        // convert to Code
        var langCode = converter(lang)
        res.addAttribute("lang", lang);
        res.addAttribute("langCode", langCode);
        return res;
    }


}