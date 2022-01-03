package net.thexcoders.aladin_bot_backend.controllers

import net.thexcoders.aladin_bot_backend.converters.LangConverter.langToCodeConverter
import net.thexcoders.aladin_bot_backend.models.Responses
import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetector
import net.thexcoders.aladin_bot_backend.nlp_models.language.LangDetectorImpl
import net.thexcoders.aladin_bot_backend.repositories.ResponseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*


/**
 * Language Controller:
 * Used to detect the language of the the user input. This supports only French and English languages. Any other language will be considered as Unknown
 */
@RestController
@RequestMapping("API/language")
@CrossOrigin("*")
class LangController {

    @Autowired
    lateinit var responseRepo: ResponseRepository


    @GetMapping("/seeder/test")
    fun seeder(): String {
        var resp = Responses("greetings", "en")
        resp.add(", My name is Aladin-Bot! How can I help you?")
        responseRepo.save(resp)
            .setLang("fr")
            .add(", Mon nom est Aladin-Bot. Comment puis-je vous aider?")
        responseRepo.save(resp)

        resp = Responses("unknown", "en")
        resp.add("I didn't understand what you mean. Can you re-phrase?")
        responseRepo.save(resp)
            .setLang("fr")
            .add("je n'ai pas compris ce que vous voulez dire. pouvez vous reformuler?")
        responseRepo.save(resp)

        resp = Responses("detectedLang", "en")
        resp.add("✔️ English detected!")
            .setLang("fr")
            .add("✔️ Français détecté !")
        responseRepo.save(resp)

        resp = Responses("greetingList", "en")
        resp.add("Hello").add("Hi").add("Hey")
            .setLang("fr")
            .add("Bonjour").add("Salut")
        responseRepo.save(resp)

        resp = Responses("makersList", "en")
        resp.add("my coders are").add("my creators are").add("my developers are")
        responseRepo.save(resp)
            .setLang("fr")
            .add("mes codeurs sont").add("mes créateurs sont").add("mes développeurs sont")
        responseRepo.save(resp)

        resp = Responses("unknownLanguage", "en")
        resp.add("I can't understand you!")
            .setLang("fr")
            .add("Je ne peux pas vous comprendre !")
        responseRepo.save(resp)

        resp = Responses("makers", "en")
        resp.add(" <strong>Hajar EL HAKOUR</strong> & <strong>Anass AIT BEN EL ARBI</strong> \uD83D\uDE01")
            .setLang("fr")
            .add(" <strong>Hajar EL HAKOUR</strong> & <strong>Anass AIT BEN EL ARBI</strong> \uD83D\uDE01")
        responseRepo.save(resp)

        resp = Responses("emergency", "en")
        resp.add(
            "<div>🚨 here are all the emergency contacts in Morocco: <ul> " +
                    "<li style='margin-left: 10px'>Police : <a href='tel:19'>19</a></li>" +
                    "<li style='margin-left: 10px'>Royal Gendarmerie : <a href='tel:177'>177</a></li>" +
                    "<li style='margin-left: 10px'>Ambulance : <a href='tel:177'>15</a></li>" +
                    "<li style='margin-left: 10px'>Fire Brigade : <a href='tel:15'>15</a></li> </ul></div>"
        )
            .setLang("fr")
            .add(
                "<div>🚨 voici tous les contacts d'urgence au Maroc : <ul> " +
                        "<li style='margin-left: 10px'>Police : <a href='tel:19'>19</a></li>" +
                        "<li style='margin-left: 10px'>Gendarmerie Royale : <a href='tel:177'>177</a></li>" +
                        "<li style='margin-left: 10px'>Ambulance : <a href='tel:177'>15</a></li>" +
                        "<li style='margin-left: 10px'>Sapeurs-pompiers: <a href='tel:15'>15</a></li> </ul></div>"
            )
        responseRepo.save(resp)

        resp = Responses("checkTime", "en")
        resp.add("Let me check the time for you ... It is").add("It is").add("Let me see my watch ... It is")
            .add("Let me ask a friend ... It's")
            .setLang("fr")
            .add("Laissez-moi vérifier l'heure pour vous... Il est").add("Il est")
            .add("Laissez-moi voir ma montre...Il est")
            .add("Laissez-moi demander à un ami... Il est")
        responseRepo.save(resp)

        resp = Responses("bestPlaces", "en")
        resp.add("There are a lot of beautiful places in Morocco Like :")
            .add("Every Moroccan place has it's beauty but you can check out ")
            .add("There are too many nice places for me to decide let me randomize it for you... Ohh I have got :")
            .setLang("fr")
            .add("Il y a beaucoup de beaux endroits au Maroc comme :")
            .add("Chaque endroit marocain a sa beauté mais vous pouvez chercher ")
            .add("Il y a trop d'endroits sympas pour que je puisse décider, laissez-moi le randomiser pour vous... Ohh j'ai :")
        responseRepo.save(resp)

        resp = Responses("places", "en")
        resp.add("Tangier")
            .add("Oualili")
            .add("Marakesh")
            .add("Fes")
            .add("Chefchaouen")
            .add("Essaouira")
            .setLang("fr")
            .add("Tangier")
            .add("Oualili")
            .add("Marakesh")
            .add("Fes")
            .add("Chefchaouen")
            .add("Essaouira")
        responseRepo.save(resp)

        resp = Responses("food", "en")
        resp.add("Tajine")
            .add("Seffa")
            .add("Tkelia")
            .add("Couscous")
            .add("harcha")
            .add("Medfouna")
            .add("merozia")
            .setLang("fr")
            .add("Tajine")
            .add("Seffa")
            .add("Tkelia")
            .add("Couscous")
            .add("harcha")
            .add("Medfouna")
            .add("merozia")
        responseRepo.save(resp)



        resp = Responses("bestFood", "en")
        resp.add("There are some delicious food like: ")
            .add("I think the most yummy food is: ")
            .add("My favorite is: ")
            .setLang("fr")
            .add("Il y a des plats délicieux comme: ")
            .add("Je pense que la nourriture la plus délicieuse est: ")
            .add("Mon préféré est: ")
        responseRepo.save(resp)

        return "Database Seeded!"

    }

    /**
     * Based on the user input the function detects the language and returns a response with the language code and a message.
     * @param data ModelMap containing data of type : {message : String}
     * @return ModelMap with two values : <code>message</code> containing a message that will be displayed to the user by the chatbot and a integer <code>langCode</code> representing the code of the language.
     *
     * @see LangDetector
     * @see langToCodeConverter(String)
     * @see ModelMap
     * */
    @PostMapping("/get")
    fun detectLang(@RequestBody data: ModelMap): ModelMap {
        // get user Input
        val res = ModelMap()
        val msg: String = data.getAttribute("message") as String

        // detect Language
        val detector = LangDetectorImpl()
        val lang = detector.detectLang(msg)

        // convert to Code
        val langCode = langToCodeConverter(lang)


        if (langCode == -1)
            this.responseRepo.findById("unknownLanguage").ifPresent { e ->
                res.addAttribute("message", (e.values["en"] as ArrayList<String>)[0])
            }
        else {
            val resp = this.responseRepo.findById("detectedLang").get();
            res.addAttribute("message", (resp.values[lang.substring(0, 2)] as ArrayList<String>)[0])
        }
        res.addAttribute("langCode", langCode)
        return res
    }
}