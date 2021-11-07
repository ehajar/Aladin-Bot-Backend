package net.thexcoders.aladin_bot_backend.converters;

public class CategoryConverter {

    public static final int UNKNOWN_CAT = -1;
    public static final int GREETINGS = 0;
    public static final int CONV_END = 1;
    public static final int EMERGENCY = 2;
    public static final int CREATE_DATA = 3;
    public static final int TIME_DATA = 4;
    public static final int BEST_PLACE = 5;
    public static final int BEST_FOOD = 6;

    /**
     * Converting the Category code to an Integer.
     *
     * @param lang String representing the category
     * @return Integer Code of the category
     */


    public static int categoryConverter(String lang) {
        switch (lang) {
            case "greeting":
                return GREETINGS;
            case "conv-end":
                return CONV_END;
            case "emergency":
                return EMERGENCY;
            case "create-data":
                return CREATE_DATA;
            case "time-data":
                return TIME_DATA;
            case "best-place":
                return BEST_PLACE;
            case "best-food":
                return BEST_FOOD;
            default:
                return UNKNOWN_CAT;
        }
    }
}

