package manager;

import material.Materials;
import java.util.Comparator;

public class CollectionSort implements Comparator<Materials> {
    @Override
    public int compare(Materials o1, Materials o2) {
        if(o1.getPrice() > o2.getPrice()){
            return 1;
        }else if(o1.getPrice() == o2.getPrice()){
            return 0;
        }else{
            return -1;
        }
    }
}