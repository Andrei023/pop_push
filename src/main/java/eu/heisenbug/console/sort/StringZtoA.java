package eu.heisenbug.console.sort;

import java.util.Comparator;

public class StringZtoA implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
