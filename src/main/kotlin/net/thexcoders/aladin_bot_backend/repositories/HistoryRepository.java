package net.thexcoders.aladin_bot_backend.repositories;

import net.thexcoders.aladin_bot_backend.models.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History,String> {


}
