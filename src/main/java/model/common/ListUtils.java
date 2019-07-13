package model.common;

import java.util.List;
import java.util.logging.Logger;

public class ListUtils {
    private Logger console;

    public ListUtils(Logger logger){
        this.console = logger;
    }

    public <T> void printList(List<T> objects) {
        for (T obj : objects) {
            try {
                System.out.println(obj.toString());
            } catch (Exception e) {
                console.severe("Error : object in list does not " +
                        "support toString()");
                console.severe(e.getMessage());
                break;
            }
        }
    }

}
