package model.common;

import org.slf4j.Logger;
import java.util.List;

public class ListUtils {

    public static <T> void printList(List<T> objects, Logger console) {
        for (T obj : objects) {
            try {
                console.info(obj.toString());
            } catch (Exception e) {
                console.error("Error : object in list does not " +
                        "support toString()");
                console.error(e.getMessage());
                break;
            }
        }
    }

}
