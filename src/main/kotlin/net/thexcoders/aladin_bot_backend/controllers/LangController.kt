package net.thexcoders.aladin_bot_backend.controllers

import net.thexcoders.aladin_bot_backend.converters.langToCodeConverter
import net.thexcoders.aladin_bot_backend.models.Responses
import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetector
import net.thexcoders.aladin_bot_backend.repositories.ResponseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("API/language")
@CrossOrigin("*")
class LangController {

    @Autowired
    lateinit var responseRepo: ResponseRepository


    @GetMapping("/test")
    fun testFill(): String {
        var resp = Responses("greetings")
        resp.add(", My name is Aladin-Bot! How can I help you?")
        responseRepo.save(resp)

        resp = Responses("unknown")
        resp.add("I didn't understand what you mean. Can you re-phrase?")
        responseRepo.save(resp)

        resp = Responses("detectedLang")
        resp.add("✔️ English detected!")
        responseRepo.save(resp)

        resp = Responses("greetingList")
        resp.add("Hello").add("Hi").add("Hey")
        responseRepo.save(resp)

        resp = Responses("makersList")
        resp.add("my coders are").add("my creators are").add("my developers are")
        responseRepo.save(resp)

        resp = Responses("unknownLanguage")
        resp.add("I can't understand you!")
        responseRepo.save(resp)

        resp = Responses("makers")
        resp.add("<strong>Hajar EL HAKOUR</strong> & <strong>Anass AIT BEN EL ARBI</strong> \uD83D\uDE01")
        responseRepo.save(resp)

        resp = Responses("emergency")
        resp.add(
            "<div>🚨 here are all the emergency contacts in Morocco: <ul> " +
                    "<li style='margin-left: 10px'>Police : <a href='tel:19'>19</a></li>" +
                    "<li style='margin-left: 10px'>Royal Gendarmerie : <a href='tel:177'>177</a></li>" +
                    "<li style='margin-left: 10px'>Ambulance : <a href='tel:177'>15</a></li>" +
                    "<li style='margin-left: 10px'>Fire Brigade : <a href='tel:15'>15</a></li> </ul></div>"
        )
        responseRepo.save(resp)

        resp = Responses("checkTime")
        resp.add("Let me check the time for you ... It is").add("It is").add("Let me see my watch ... It is")
            .add("Let me ask a friend ... It's")
        responseRepo.save(resp)

        resp = Responses("bestPlaces")
        resp.add("There are a lot of beautifull places in Morocco Like :")
            .add("Every Moroccan place has it's beauty but you can check out ")
            .add("There are too many nice places for me to decide let me randomize it for you... Ohh I have got :")
        responseRepo.save(resp)

        resp = Responses("places")
        resp.add("Tangier")
            .add("Oualili")
            .add("Marakesh")
            .add("Fes")
            .add("Chefchaouen")
            .add("Essaouira")
        responseRepo.save(resp)

        return "ok";

    }


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
        res.addAttribute(
            "message", when (langCode) {
                -1 -> this.responseRepo.findById("unknownLanguage").get().values[0]
                else -> this.responseRepo.findById("detectedLang").get().values[0]
            }
        )
        res.addAttribute("langCode", langCode)
        return res;
    }


}