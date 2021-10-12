package net.thexcoders.AladinBotBackend

import opennlp.tools.sentdetect.SentenceDetectorME
import opennlp.tools.sentdetect.SentenceModel
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File


@SpringBootApplication
class AladinBotBackEndApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        val path = "src/main/resources/";
        val file = File(path+"sent.bin")
        val paragraph = ("This is a statement. This is another statement."
                + "Now is an abstract word for time, "
                + "that is always flying. And my email address is google@gmail.com.")

        val model = SentenceModel(file)
        val sDetector = SentenceDetectorME(model)
        val sentences: Array<String> = sDetector.sentDetect(paragraph)
        sentences.forEach { x: String -> System.err.println(x) }

    }
}

fun main(args: Array<String>) {
    runApplication<AladinBotBackEndApplication>(*args)
}
