package eu.heisenbug.sort;

public class IntegerMinimum extends AbstractComparator<Integer> {

    public IntegerMinimum() {
        super(Integer.class);
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }

    @Override
    public Integer parseElement(String element) {
        return Integer.valueOf(element);
    }
}
