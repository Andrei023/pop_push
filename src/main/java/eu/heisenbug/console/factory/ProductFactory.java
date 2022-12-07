package eu.heisenbug.console.factory;

import eu.heisenbug.console.constants.OrderStrategy;
import eu.heisenbug.console.constants.ProductType;
import eu.heisenbug.product.AbstractOrderedList;
import eu.heisenbug.product.QuickPopOrderedList;
import eu.heisenbug.product.QuickPushOrderedList;
import eu.heisenbug.console.sort.IntegerMaximum;
import eu.heisenbug.console.sort.IntegerMinimum;
import eu.heisenbug.console.sort.StringAtoZ;
import eu.heisenbug.console.sort.StringZtoA;

public class ProductFactory {

    private ProductFactory() {
    }

    public static AbstractOrderedList<Integer> getIntegerProduct(ProductType productType, OrderStrategy orderStrategy) {
        if (OrderStrategy.ASCENDING.equals(orderStrategy)) {
            return ProductType.QUICK_POP.equals(productType) ? new QuickPopOrderedList<>(new IntegerMinimum()) :
                    new QuickPushOrderedList<>(new IntegerMinimum());
        }
        return ProductType.QUICK_POP.equals(productType) ? new QuickPopOrderedList<>(new IntegerMaximum()) :
                new QuickPushOrderedList<>(new IntegerMaximum());

    }

    public static AbstractOrderedList<String> getStringProduct(ProductType productType, OrderStrategy orderStrategy) {
        if (OrderStrategy.ASCENDING.equals(orderStrategy)) {
            return ProductType.QUICK_POP.equals(productType) ? new QuickPopOrderedList<>(new StringAtoZ()) :
                    new QuickPushOrderedList<>(new StringAtoZ());
        }
        return ProductType.QUICK_POP.equals(productType) ? new QuickPopOrderedList<>(new StringZtoA()) :
                new QuickPushOrderedList<>(new StringZtoA());
    }
}
