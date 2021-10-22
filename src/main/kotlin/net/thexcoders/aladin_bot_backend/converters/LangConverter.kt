package net.thexcoders.aladin_bot_backend.converters

import net.thexcoders.aladin_bot_backend.nlp_models.ModelsParent


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

fun codeToLangConverter(code: Int): ModelsParent.Language {
    return when (code) {
        EN -> ModelsParent.Language.EN
        FR -> ModelsParent.Language.FR
        else -> ModelsParent.Language.EN
    }
}