package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceUtil {
    public static <T> void saveList(List<T> list, String filename) {
        try (ObjectOutputStream oos =
               new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> loadList(String filename) {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois =
               new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
