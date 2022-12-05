package eu.heisenbug.product;

import eu.heisenbug.list.Node;
import eu.heisenbug.sort.AbstractComparator;

public class QuickPushOrderedList<T> extends AbstractOrderedList<T> {

    public QuickPushOrderedList(AbstractComparator<T> sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    @Override
    public T pop() {
        synchronized (this.lock) {
            Node<T> current = this.getHead();
            if (current == null) {
                return null;
            }
            T max = current.getValue();
            Node<T> previousToPop = current;

            while (current.getNext() != null) {
                if (sortAlgorithm.compare(current.getNext().getValue(), max) > 0) {
                    max = current.getNext().getValue();
                    previousToPop = current;
                }
                current = current.getNext();
            }

            if (max.equals(this.getHead().getValue())) {
                this.head = this.head.getNext();
            } else {
                previousToPop.setNext(previousToPop.getNext().getNext());
            }
            return max;
        }
    }

    @Override
    public void push(T element) {
        synchronized (this.lock) {
            this.head = new Node<>(element, this.head);
        }
    }

    private Node<T> getHead() {
        return this.head;
    }
}
