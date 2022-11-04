package manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO<T> {
    public FileIO() {
    }
    public List<T> readFile(String path) {
        List<T> list = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream(path);
            if (in.available()>0){
                ObjectInputStream ois = new ObjectInputStream(in);
                list = (List<T>) ois.readObject();
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return list;
    }
    public void writeFile(List<T> list,String path){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}