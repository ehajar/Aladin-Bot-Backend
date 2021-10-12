package net.thexcoders.AladinBotBackend.controller

import net.thexcoders.AladinBotBackend.models.Test
import net.thexcoders.AladinBotBackend.repositories.TestRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @Autowired
    lateinit var testRepo : TestRepo;

    @GetMapping("/test")
    fun test() : String {
        testRepo.save(Test("Helloo"));
        testRepo.save(Test("World!"));
        return "TEST SUCCESSFULL";
    }

    @GetMapping("/testData")
    fun testData(): MutableList<Test> {
        return testRepo.findAll();
    }
}