package eu.heisenbug.product;

import eu.heisenbug.list.Node;
import eu.heisenbug.sort.AbstractComparator;

public abstract class AbstractOrderedList<T> {

    protected final Object lock = new Object();

    Node<T> head;
    AbstractComparator<T> sortAlgorithm;

    public abstract T pop();

    public abstract void push(T element);

    public AbstractComparator<T> getSortAlgorithm() {
        return sortAlgorithm;
    }

    // for testing / UI purposes
    public void printElements() {
        Node<T> first = this.getHead();
        if (first == null) {
            System.out.println("[]");
            return;
        }
        System.out.print("[" + first.getValue());
        Node<T> current = first;
        while (current.getNext() != null) {
            System.out.print(", " + current.getNext().getValue());
            current = current.getNext();
        }
        System.out.println("]");
    }

    // for testing purposes
    String getElements() {
        Node<T> first = this.getHead();
        if (first == null) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[").append(first.getValue());
        Node<T> current = first;
        while (current.getNext() != null) {
            result.append(", ").append(current.getNext().getValue());
            current = current.getNext();
        }
        result.append("]");
        return result.toString();
    }

    private Node<T> getHead() {
        return this.head;
    }
}
