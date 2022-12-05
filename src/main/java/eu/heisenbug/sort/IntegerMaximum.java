package eu.heisenbug.sort;

public class IntegerMaximum extends AbstractComparator<Integer> {

    public IntegerMaximum() {
        super(Integer.class);
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }

    @Override
    public Integer parseElement(String element) {
        return Integer.valueOf(element);
    }
}
