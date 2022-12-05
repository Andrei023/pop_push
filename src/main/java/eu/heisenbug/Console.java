package eu.heisenbug;

import eu.heisenbug.constants.DataType;
import eu.heisenbug.constants.OrderStrategy;
import eu.heisenbug.constants.ProductType;
import eu.heisenbug.factory.ProductFactory;
import eu.heisenbug.product.AbstractOrderedList;

import java.util.Scanner;

public class Console {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String nextLine = "";

        System.out.println("'1' for Integer");
        System.out.println("'2' for String");
        System.out.println("'q' to Quit the program");
        System.out.print("Choose your data type: ");

        DataType dataType;
        while (true) {
            nextLine = scanner.nextLine();
            if (nextLine.equals("q")) {
                return;
            }
            if (nextLine.equals("1")) {
                dataType = DataType.INTEGER;
                break;
            }
            if (nextLine.equals("2")) {
                dataType = DataType.STRING;
                break;
            }
            System.out.print("Invalid option. Try again: ");
        }

        System.out.println("'1' for Ascending order strategy");
        System.out.println("'2' for Descending order strategy");
        System.out.println("'q' to Quit the program");
        System.out.print("Choose your order strategy: ");

        OrderStrategy orderStrategy;
        while (true) {
            nextLine = scanner.nextLine();
            if (nextLine.equals("q")) {
                return;
            }
            if (nextLine.equals("1")) {
                orderStrategy = OrderStrategy.ASCENDING;
                break;
            }
            if (nextLine.equals("2")) {
                orderStrategy = OrderStrategy.DESCENDING;
                break;
            }
            System.out.print("Invalid option. Try again: ");
        }

        System.out.println("'1' for QuickPop");
        System.out.println("'2' for QuickPush");
        System.out.println("'q' to Quit the program");
        System.out.print("Choose your product: ");

        ProductType productType;
        while (true) {
            nextLine = scanner.nextLine();
            if (nextLine.equals("q")) {
                return;
            }
            if (nextLine.equals("1")) {
                productType = ProductType.QUICK_POP;
                break;
            }
            if (nextLine.equals("2")) {
                productType = ProductType.QUICK_PUSH;
                break;
            }
            System.out.print("Invalid option. Try again: ");
        }

        AbstractOrderedList<?> product = ProductFactory.getProduct(productType, dataType, orderStrategy);

        if (product == null) {
            System.out.println("Error encountered: product is null.");
            return;
        }

        System.out.println("Created product " + productType.getLabel().toUpperCase() + " with data type " + dataType +
                " and order strategy " + orderStrategy.getLabel().toUpperCase());

        while (true) {
            System.out.println("'1' for pop (delete)");
            System.out.println("'2' for push (insert)");
            System.out.println("'q' to Quit the program");
            System.out.print("Choose option: ");

            nextLine = scanner.nextLine();

            switch (nextLine) {
                case "q":
                    return;
                case "1":
                    Object element = product.pop();
                    if (element != null) {
                        System.out.println(element + " popped.");
                    }
                    product.printElements();
                    break;
                case "2":
                    System.out.print("Element: ");
                    String input = scanner.nextLine();
                    product.push(product.getSortAlgorithm().parseElement(input));
                    product.printElements();
                    break;
                default:
                    System.out.println("Invalid option. Try again: ");
                    break;
            }
        }
    }
}
