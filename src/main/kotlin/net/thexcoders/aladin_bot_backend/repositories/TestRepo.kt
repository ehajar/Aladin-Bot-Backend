package net.thexcoders.aladin_bot_backend.repositories

import net.thexcoders.aladin_bot_backend.models.Test
import org.springframework.data.mongodb.repository.MongoRepository

interface TestRepo : MongoRepository<Test,String>{

}