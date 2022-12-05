package eu.heisenbug.sort;

import java.util.Comparator;

public abstract class AbstractComparator<T> implements Comparator<T> {

    Class<T> type;

    public AbstractComparator(Class<T> type) {
        this.type = type;
    }

    public abstract <T> T parseElement(String element);
}
