package eu.heisenbug.product;

import eu.heisenbug.list.Node;

import java.util.Comparator;

public abstract class AbstractOrderedList<T> {

    protected final Object lock = new Object();

    Node<T> head;
    Comparator<T> sortAlgorithm;

    public abstract T pop();

    public abstract void push(T element);

    public Node<T> getHead() {
        return this.head;
    }
}
