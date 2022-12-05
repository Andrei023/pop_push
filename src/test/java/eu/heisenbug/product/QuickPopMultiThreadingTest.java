package eu.heisenbug.product;


import eu.heisenbug.constants.DataType;
import eu.heisenbug.constants.OrderStrategy;
import eu.heisenbug.constants.ProductType;
import eu.heisenbug.factory.ProductFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuickPopMultiThreadingTest {

    AbstractOrderedList<?> underTest;

    @BeforeEach
    void init() {
        underTest = ProductFactory.getProduct(ProductType.QUICK_POP, DataType.INTEGER, OrderStrategy.DESCENDING);
        assertNotNull(underTest);
    }

    @Test
    public void GIVEN_quickPop_WHEN_multipleThreadsRun_THEN_elementsAreStoredInOrder() {
        final CountDownLatch latch = new CountDownLatch(1);
        int threads = 50;
        int elementsPushedPerThread = 200;
        int elementsPoppedPerThread = 0;

        // otherwise parsing the list to inspect elements fails
        assertTrue(elementsPushedPerThread > elementsPoppedPerThread);

        List<Thread> threadsList = new ArrayList<>();
        for (int i = 0; i < threads; ++i) {
            Runnable runner = () -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Random random = new Random();
                for (int j = 0; j < elementsPushedPerThread; j++) {
                    underTest.push(underTest.getSortAlgorithm().parseElement(String.valueOf(random.nextInt())));
                }
                for (int j = 0; j < elementsPoppedPerThread; j++) {
                    underTest.pop();
                }
            };
            Thread thread = new Thread(runner, "TestThread" + i);
            threadsList.add(thread);
            thread.start();
        }
        // all threads are waiting on the latch.
        // release the latch
        latch.countDown();
        // all threads are now running concurrently.

        // wait for all threads to finish
        for (Thread thread : threadsList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // get all elements
        String result = underTest.getElements();

        // remove '[' and ']' and split by ', '
        List<Integer> elements = Arrays.stream(result.substring(1, result.length() - 1).split(", "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        // assert element count at the end is correct
        int expectedSize = threads * (elementsPushedPerThread - elementsPoppedPerThread);
        assertEquals(expectedSize, elements.size());

        // assert elements are ordered
        boolean ordered = true;
        for (int i = 0; i < elements.size() - 1; i++) {
            if (elements.get(i) < elements.get(i + 1)) {
                ordered = false;
                break;
            }
        }
        assertTrue(ordered);
    }
}
