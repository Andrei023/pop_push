package eu.heisenbug.util;

import eu.heisenbug.list.Node;

public class ListHelper {

    private ListHelper() {
    }

    public static void printElements(Node<?> head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        System.out.print("[" + head.getValue());
        Node<?> current = head;
        while (current.getNext() != null) {
            System.out.print(", " + current.getNext().getValue());
            current = current.getNext();
        }
        System.out.println("]");
    }

    public static String getElements(Node<?> head) {
        if (head == null) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[").append(head.getValue());
        Node<?> current = head;
        while (current.getNext() != null) {
            result.append(", ").append(current.getNext().getValue());
            current = current.getNext();
        }
        result.append("]");
        return result.toString();
    }
}
