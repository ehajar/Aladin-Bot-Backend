package net.thexcoders.aladin_bot_backend.controllers

import net.thexcoders.aladin_bot_backend.converters.codeToLangConverter
import net.thexcoders.aladin_bot_backend.models.History
import net.thexcoders.aladin_bot_backend.nlp_models.ModelsParent
import net.thexcoders.aladin_bot_backend.repositories.HistoryRepository
import net.thexcoders.aladin_bot_backend.repositories.ResponseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("API/coms")
@CrossOrigin("*")
class CommunicationController {

    @Autowired
    lateinit var responseRepo: ResponseRepository;

    @Autowired
    private lateinit var historyRepository: HistoryRepository


    @PostMapping("/communicate")
    fun communicate(@RequestBody data: ModelMap): ArrayList<String> {
        // input params
        val message = data.getAttribute("message") as String;
        val langCode = data.getAttribute("langCode") as Int;

        // instantiate Classes
        val languageStr = codeToLangConverter(langCode)
        val model = ModelsParent(languageStr)

        // processing the data;
        val sentences = model.toSentences(message)
        val respList = model.getCategories(sentences)

        val res = ArrayList<String>()
        for (x in respList) {
            res.add(getFromCode(x.catCode))
            historyRepository.save(History(x.input, x,languageStr.value))
        }
        return res;

    }

    fun getFromCode(catCode: Number): String {
        when (catCode) {
            -1 ->
                return randomise("unknown")
            0 ->
                return randomise("greetingList") + randomise("greetings")
            2 ->
                return randomise("emergency")
            3 ->
                return randomise("makersList") + randomise("makers")
            4 ->
                return randomise("checkTime") + " " + Date().toLocaleString();
            5 ->
                return randomise("bestPlaces") + " " + randomise("places");
            6 ->
                return randomise("bestPlaces") + " " + randomise("places");
        }
        return "";
    }

    fun randomise(id: String): String {
        val list = this.responseRepo.findById(id).get().values;
        if (list.size == 1) {
            return list[0]
        }
        val rand = Math.random() * (list.size - 1)
        return list[rand.toInt()];
    }

}