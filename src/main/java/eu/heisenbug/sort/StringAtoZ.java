package eu.heisenbug.sort;

public class StringAtoZ extends AbstractComparator<String> {

    public StringAtoZ() {
        super(String.class);
    }

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }

    @Override
    public String parseElement(String element) {
        return element;
    }
}
