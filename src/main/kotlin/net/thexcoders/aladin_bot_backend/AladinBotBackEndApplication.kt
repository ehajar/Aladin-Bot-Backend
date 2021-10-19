package net.thexcoders.aladin_bot_backend

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class AladinBotBackEndApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        /*val path = "src/main/resources/";

        val file = File(path+"sentence.bin")

        val paragraph = ("This is a statement. This is another statement. Now is an abstract word for time, that is always flying. And my email address is google@gmail.com.")

        val model = SentenceModel(file)
        val sDetector = SentenceDetectorME(model)
        val sentences: Array<String> = sDetector.sentDetect(paragraph)
        System.out.flush();
        sentences.forEach { x: String -> System.err.println(x) }*/

    }
}

fun main(args: Array<String>) {
    runApplication<AladinBotBackEndApplication>(*args)
}
