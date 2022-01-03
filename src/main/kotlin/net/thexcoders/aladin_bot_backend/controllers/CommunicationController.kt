package net.thexcoders.aladin_bot_backend.controllers

import net.thexcoders.aladin_bot_backend.converters.LangConverter.codeToLangConverter
import net.thexcoders.aladin_bot_backend.models.History
import net.thexcoders.aladin_bot_backend.nlp_models.ModelsOrchestrer
import net.thexcoders.aladin_bot_backend.nlp_models.ModelsOrchestrerImpl
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.CategorizerImpl.CategoryResult
import net.thexcoders.aladin_bot_backend.repositories.HistoryRepository
import net.thexcoders.aladin_bot_backend.repositories.ResponseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Communication Controller:
 * represents all the direct communications between the client and the chatbot to get the replies based on their request and based on the trained Model
 * @see ModelsOrchestrer
 * */
@RestController
@RequestMapping("API/coms")
@CrossOrigin("*")
class CommunicationController {

    @Autowired
    lateinit var responseRepo: ResponseRepository

    @Autowired
    private lateinit var historyRepository: HistoryRepository


    /**
     * Communicate method is to get the data from the user process  it, get the result and then return it back to the user
     * @param data ModelMap the input is a json file with the following scheme:
     * { message : String,langCode : Integer }
     * @return ArrayList<String> for each sentence of the input, we have a specific reply for the user. Replies are not repeated
     * {@link net.thexcoders.aladin_bot_backend.nlp_models.ModelsParent#initEng(String[]) English Initializer}
     * {@link net.thexcoders.aladin_bot_backend.nlp_models.ModelsParent#initFra(String[]) French Initializer}
     * uses {@link #getFromCode(String, String) Category from string}
     * @see ModelMap
     * @see ArrayList
     * */
    @PostMapping("/communicate")
    fun communicate(@RequestBody data: ModelMap): ArrayList<String> {
        // input params
        val message = data.getAttribute("message") as String
        val langCode = data.getAttribute("langCode") as Int

        // instantiate Classes
        val languageStr = codeToLangConverter(langCode)
        val model = ModelsOrchestrerImpl(languageStr)

        // processing the data
        val sentences = model.toSentences(message)
        val respList = model.getCategories(sentences)

        // preparing the data and saving to History
        val res = ArrayList<String>()
        for (x in respList) {
            res.add(getFromCode(x.catCode, languageStr.languageValue))
            historyRepository.save(History(x.input, x, languageStr.languageValue))
        }
        return res
    }

    /**
     * preparing the result from the category Code and the language
     * {@link #randomise(String, String) randomiser}
     * @param catCode an integer that represents the categoryCode
     * @param lang a string representing the language destined to be displayed to the user
     * @return String the result to be displayed to the user.
     */
    private fun getFromCode(catCode: Number, lang: String): String {
        when (catCode) {
            -1 ->
                return randomise("unknown", lang)
            0 ->
                return randomise("greetingList", lang) + randomise("greetings", lang)
            2 ->
                return randomise("emergency", lang)
            3 ->
                return randomise("makersList", lang) + randomise("makers", lang)
            4 ->
                return randomise("checkTime", lang) + " " + Date();
            5 ->
                return randomise("bestPlaces", lang) + " " + randomise("places", lang)
            6 ->
                return randomise("bestFood", lang) + " " + randomise("food", lang)
        }
        return ""
    }

    /**
     * A function that randomizes the pick of one of the elements of the list retrieved from the database by Id
     * @param id  A String that represents the Id of the response
     * @param lang A string that represents the language desired for the response
     *
     * @return a String containing the result to display to the user
     * */
    private fun randomise(id: String, lang: String): String {
        val list = this.responseRepo.findById(id).get().values[lang] ?: return ""
        if (list.size == 1) {
            return list[0]
        }
        val rand = Math.random() * (list.size - 1)
        return list[rand.toInt()]
    }

}