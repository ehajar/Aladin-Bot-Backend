package net.thexcoders.aladin_bot_backend.helper_classes;

/**
 * Enumeration of the states of the History
 *
 * @see net.thexcoders.aladin_bot_backend.models.History
 */
public enum HistoryState {
    VALIDATED("valid"), REJECTED("rejected"), WAITING("waiting");
    public final String stateValue;

    /**
     * Constructor of the Enumeration
     *
     * @param stateValue A string representing the value of the enumeration
     */
    HistoryState(String stateValue) {
        this.stateValue = stateValue;

    }
}
