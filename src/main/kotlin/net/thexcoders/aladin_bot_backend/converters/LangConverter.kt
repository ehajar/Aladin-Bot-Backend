package net.thexcoders.aladin_bot_backend.converters

import net.thexcoders.aladin_bot_backend.nlp_models.ModelsParent
import net.thexcoders.aladin_bot_backend.nlp_models.categorizer.Categorizer


const val UNKNOWN = -1;
const val FR = 0;
const val EN = 1;
const val AR = 2;

fun langToCodeConverter(lang: String): Int {
    return when (lang) {
        "eng" -> EN
        "fra" -> FR
        "ara" -> AR
        else -> UNKNOWN
    }
}

fun codeToLangConverter(code: Int): Categorizer.Language {
    return when (code) {
        EN -> Categorizer.Language.EN
        FR -> Categorizer.Language.FR
        else -> Categorizer.Language.EN
    }
}