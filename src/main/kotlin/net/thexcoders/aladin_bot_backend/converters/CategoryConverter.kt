package net.thexcoders.aladin_bot_backend.converters

const val UNKNOWN_CAT = -1;
const val GREETINGS = 0;
const val CONV_END = 1;
const val EMERGENCY = 2;
const val CREATE_DATA = 3;
const val TIME_DATA = 4;
const val BEST_PLACE = 5;
const val BEST_FOOD = 6;

fun categoryConverter(lang: String): Int {
    return when (lang) {
        "greeting" -> GREETINGS
        "conv-end" -> CONV_END
        "emergency" -> EMERGENCY
        "create-data" -> CREATE_DATA
        "time-data" -> TIME_DATA
        "best-place" -> BEST_PLACE
        "best-food " -> BEST_FOOD
        else -> UNKNOWN_CAT
    }
}