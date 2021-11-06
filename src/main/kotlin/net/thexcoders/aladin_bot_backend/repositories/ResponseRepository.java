package net.thexcoders.aladin_bot_backend.repositories;

import net.thexcoders.aladin_bot_backend.models.Responses;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResponseRepository extends MongoRepository<Responses,String> {

}
