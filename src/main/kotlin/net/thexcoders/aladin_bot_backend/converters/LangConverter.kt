package net.thexcoders.aladin_bot_backend.converters

class LangConverter {
}

const val UNKNOWN = -1;
const val FR = 0;
const val EN = 1;
const val AR = 2;

fun converter(lang : String) : Int{
    return when (lang){
        "eng"-> EN
        "fra" -> FR
        "ara" -> AR
        else -> UNKNOWN
    }
}