import java.util.ArrayList;
import java.util.Iterator;

public class Lec1Rev {
    public static void main(String[] args) {
        ArrayList<Integer> integerArrayList =
                new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++) {
            integerArrayList.add(i);
        }

/*
        for (int i = 0 ; i < integerArrayList.size();
             i++) {
           */
/* if (i == 5) {
                integerArrayList.remove(i);
            }*//*

            if (i %2 == 0) {
                integerArrayList.remove(i);
                i++;
            }
        }
*/

        Iterator<Integer> it = integerArrayList.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) {
                it.remove();
            }
        }

        for (Integer listElement : integerArrayList) {
            System.out.println(listElement);
        }

    }
}
