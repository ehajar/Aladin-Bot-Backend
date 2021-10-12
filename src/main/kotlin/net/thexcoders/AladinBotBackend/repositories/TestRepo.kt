package net.thexcoders.AladinBotBackend.repositories

import net.thexcoders.AladinBotBackend.models.Test
import org.springframework.data.mongodb.repository.MongoRepository

interface TestRepo : MongoRepository<Test,String>{

}