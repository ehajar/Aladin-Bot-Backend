package net.thexcoders.aladin_bot_backend.models

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Test")
data class Test(val content : String){

    fun getId(): String {
        return content;
    }
}
