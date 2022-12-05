package eu.heisenbug.product;

import eu.heisenbug.constants.DataType;
import eu.heisenbug.constants.OrderStrategy;
import eu.heisenbug.constants.ProductType;
import eu.heisenbug.factory.ProductFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuickPushMultiThreadingTest {
    AbstractOrderedList<?> underTest;

    @BeforeEach
    void init() {
        underTest = ProductFactory.getProduct(ProductType.QUICK_PUSH, DataType.INTEGER, OrderStrategy.DESCENDING);
        assertNotNull(underTest);
    }

    @Test
    public void GIVEN_quickPush_WHEN_multipleThreadsRun_THEN_elementsArePoppedInOrder() {
        final CountDownLatch latch = new CountDownLatch(1);
        int threads = 200;
        int elementsPushedPerThread = 69;
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
                    underTest.push(underTest.getSortAlgorithm().parseElement(String.valueOf(random.nextInt(100000))));
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
        List<Integer> elements =
                Collections.synchronizedList(Arrays.stream(result.substring(1, result.length() - 1).split(", "))
                        .map(Integer::valueOf)
                        .collect(Collectors.toList()));

        // assert element count at the end is correct
        int expectedSize = threads * (elementsPushedPerThread - elementsPoppedPerThread);
        assertEquals(expectedSize, elements.size());

        // sort the list
        elements.sort(Collections.reverseOrder());

        // pop elements and check if they are popped in order
        List<Thread> threadsList2 = new ArrayList<>();
        List<Integer> poppedList = Collections.synchronizedList(new ArrayList<>());
        Object poppedLock = new Object();
        for (int i = 0; i < threads; ++i) {
            Runnable runner = () -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int j = 0; j < elementsPushedPerThread - elementsPoppedPerThread; j++) {
                    synchronized (poppedLock) {
                        int poppedElement = (int) underTest.pop();
                        poppedList.add(poppedElement);
                    }
                }
            };
            Thread thread = new Thread(runner, "TestThread" + i);
            threadsList2.add(thread);
            thread.start();
        }
        latch.countDown();
        for (Thread thread : threadsList2) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        assertEquals(elements.size(), poppedList.size());

        boolean ordered = true;
        for (int i = 0; i < elements.size(); i++) {
            if (!elements.get(i).equals(poppedList.get(i))) {
                ordered = false;
                break;
            }
        }
        assertTrue(ordered);
    }
}
