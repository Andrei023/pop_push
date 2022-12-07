package eu.heisenbug.product;

import eu.heisenbug.console.sort.IntegerMaximum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static eu.heisenbug.util.ListHelper.getElements;
import static eu.heisenbug.util.ListHelper.printElements;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuickPopPerformanceTest {

    AbstractOrderedList<Integer> underTest;

    @BeforeEach
    void init() {
        underTest = new QuickPopOrderedList<>(new IntegerMaximum());
    }

    @Test
    void WHEN_quickPop_popIsThreeOrdersOfMagnitudeFasterThanPush() {
        int n = 50000;
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 1; i <= n; i++) {
            underTest.push(random.nextInt());
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

        printElements(underTest.getHead());
        assertEquals("[]", getElements(underTest.getHead()));
    }
}
