package eu.heisenbug.sort;

public class StringZtoA extends AbstractComparator<String> {

    public StringZtoA() {
        super(String.class);
    }

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

    @Override
    public String parseElement(String element) {
        return element;
    }
}
