package io.javasmithy.util;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameLog {
    private static ObservableList<String> logList = FXCollections.observableArrayList();

    public static void addEntry(String entry){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                logList.add(entry);
            }
        });
    }

    public static ObservableList<String> getLogList(){
        return logList;
    }

}
