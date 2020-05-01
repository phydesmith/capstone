package io.javasmithy.util;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class allows anywhere in the program to add an entry to the log displayed on the GUI
 * @author Peter Hyde-Smith
 */
public class GameLog {
    /** This is an observable list from FX collections that is referenced by the listviews in the Scenes.
     *
     */
    private static ObservableList<String> logList = FXCollections.observableArrayList();

    /**Adds an entry to the end of the loglist - uses Platform.runLater to ensure it is executed on the GUI thread.
     * @param entry text to to display
     */
    public static void addEntry(String entry){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                logList.add(entry);
            }
        });
    }

    /**Returns the log list called by Controllers to set ListView observable items.
     * @return ObservableList of type String
     */
    public static ObservableList<String> getLogList(){
        return logList;
    }

}
