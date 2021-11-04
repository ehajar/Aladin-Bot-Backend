package net.thexcoders.aladin_bot_backend.helper_classes;

public enum HistoryState {
    VALIDATED("valid"), REJECTED("rejected") , WAITING("waiting");
    public final String state;

   HistoryState(String state){
        this.state=state;

    }
}
