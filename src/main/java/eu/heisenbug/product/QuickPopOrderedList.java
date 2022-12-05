package eu.heisenbug.product;

import eu.heisenbug.list.Node;
import eu.heisenbug.sort.AbstractComparator;

public class QuickPopOrderedList<T> extends AbstractOrderedList<T> {

    public QuickPopOrderedList(AbstractComparator<T> sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    @Override
    public T pop() {
        synchronized (this.lock) {
            if (this.head == null) {
                return null;
            }
            T currentHeadValue = this.head.getValue();
            this.head = this.head.getNext();
            return currentHeadValue;
        }
    }

    @Override
    public void push(T element) {
        synchronized (this.lock) {
            Node<T> current = this.head;

            if (current == null || sortAlgorithm.compare(element, current.getValue()) > 0) {
                this.insertHead(element);
                return;
            }

            while (current.getNext() != null) {
                if (sortAlgorithm.compare(element, current.getNext().getValue()) > 0) {
                    this.insertNode(current, element);
                    return;
                }
                current = current.getNext();
            }

            this.insertNode(current, element);
        }
    }

    private void insertNode(Node<T> location, T element) {
        Node<T> newNode = new Node<>(element);
        newNode.setNext(location.getNext());
        location.setNext(newNode);
    }

    private void insertHead(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.setNext(this.head);
        this.head = newNode;
    }
}
