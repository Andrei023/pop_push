package eu.heisenbug.product;


import eu.heisenbug.constants.DataType;
import eu.heisenbug.constants.OrderStrategy;
import eu.heisenbug.constants.ProductType;
import eu.heisenbug.factory.ProductFactory;
import eu.heisenbug.sort.AbstractComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuickPopPerformanceTest {

    AbstractOrderedList<?> underTest;

    @BeforeEach
    void init() {
        underTest = ProductFactory.getProduct(ProductType.QUICK_POP, DataType.INTEGER, OrderStrategy.DESCENDING);
        assertNotNull(underTest);
    }

    @Test
    public void WHEN_quickPop_popIsThreeOrdersOfMagnitudeFasterThanPush() {
        int n = 50000;
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 1; i <= n; i++) {
            underTest.push(this.getSort().parseElement(String.valueOf(random.nextInt())));
        }
        long endTime = System.nanoTime();
        long durationPush = (endTime - startTime);
        System.out.println("Time to push " + n + " elements " + (double) durationPush / 1000000000);

        long startTime2 = System.nanoTime();
        for (int i = 1; i <= n; i++) {
            underTest.pop();
        }
        long endTime2 = System.nanoTime();
        long durationPop = (endTime2 - startTime2);
        System.out.println("Time to pop " + n + " elements " + (double) durationPop / 1000000000);

        underTest.printElements();
        assertEquals("[]", underTest.getElements());
    }

    private AbstractComparator<?> getSort() {
        return underTest.getSortAlgorithm();
    }
}
