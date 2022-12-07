package eu.heisenbug.product;

import eu.heisenbug.console.sort.IntegerMaximum;
import eu.heisenbug.console.sort.StringAtoZ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static eu.heisenbug.util.ListHelper.getElements;
import static eu.heisenbug.util.ListHelper.printElements;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuickPopLogicTest {

    AbstractOrderedList<Integer> integerProduct;

    @BeforeEach
    void init() {
        integerProduct = new QuickPopOrderedList<>(new IntegerMaximum());
    }

    @Test
    void GIVEN_quickPopIntegerMax_WHEN_emptyPop_THEN_noErrors() {
        integerProduct.pop();
        assertEquals("[]", getElements(integerProduct.getHead()));
    }

    @Test
    void GIVEN_quickPopIntegerMax_WHEN_popFromSingleElementList_THEN_listIsEmpty() {
        integerProduct.push(23);
        integerProduct.pop();
        printElements(integerProduct.getHead());
        assertEquals("[]", getElements(integerProduct.getHead()));
    }

    @Test
    void GIVEN_quickPopIntegerMax_WHEN_popDuplicate_THEN_noErrors() {
        integerProduct.push(23);
        integerProduct.push(23);
        int result = (int) integerProduct.pop();
        assertEquals(23, result);
        printElements(integerProduct.getHead());
        assertEquals("[23]", getElements(integerProduct.getHead()));
    }

    @Test
    void GIVEN_quickPopIntegerMax_WHEN_popFromMultipleElementList_THEN_maxIsPopped() {
        integerProduct.push(23);
        integerProduct.push(7);
        integerProduct.push(15);
        int result1 = (int) integerProduct.pop();
        int result2 = (int) integerProduct.pop();
        int result3 = (int) integerProduct.pop();
        assertEquals(23, result1);
        assertEquals(15, result2);
        assertEquals(7, result3);
        printElements(integerProduct.getHead());
        assertEquals("[]", getElements(integerProduct.getHead()));
    }

    @Test
    void GIVEN_quickPopStringAtoZ_WHEN_popFromMultipleElementList_THEN_maxIsPopped() {
        AbstractOrderedList<String> stringProduct = new QuickPopOrderedList<>(new StringAtoZ());
        assertNotNull(integerProduct);
        stringProduct.push("def");
        stringProduct.push("xyz");
        stringProduct.push("abc");
        String result1 = stringProduct.pop();
        String result2 = stringProduct.pop();
        String result3 = stringProduct.pop();
        assertEquals("abc", result1);
        assertEquals("def", result2);
        assertEquals("xyz", result3);
        printElements(integerProduct.getHead());
        assertEquals("[]", getElements(integerProduct.getHead()));
    }
}
