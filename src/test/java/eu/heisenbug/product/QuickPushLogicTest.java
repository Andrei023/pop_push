package eu.heisenbug.product;


import eu.heisenbug.constants.DataType;
import eu.heisenbug.constants.OrderStrategy;
import eu.heisenbug.constants.ProductType;
import eu.heisenbug.factory.ProductFactory;
import eu.heisenbug.sort.AbstractComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuickPushLogicTest {

    AbstractOrderedList<?> underTest;

    @BeforeEach
    void init() {
        underTest = ProductFactory.getProduct(ProductType.QUICK_PUSH, DataType.INTEGER, OrderStrategy.DESCENDING);
        assertNotNull(underTest);
    }

    @Test
    public void GIVEN_quickPushIntegerMax_WHEN_emptyPop_THEN_noErrors() {
        underTest.pop();
    }

    @Test
    public void GIVEN_quickPushIntegerMax_WHEN_popFromSingleElementList_THEN_listIsEmpty() {
        underTest.push(this.getSort().parseElement("23"));
        underTest.pop();
        underTest.printElements();
        assertEquals("[]", underTest.getElements());
    }

    @Test
    public void GIVEN_quickPushIntegerMax_WHEN_popDuplicate_THEN_noErrors() {
        underTest.push(this.getSort().parseElement("23"));
        underTest.push(this.getSort().parseElement("23"));
        int result = (int) underTest.pop();
        assertEquals(23, result);
        underTest.printElements();
        assertEquals("[23]", underTest.getElements());
    }

    @Test
    public void GIVEN_quickPushIntegerMax_WHEN_popFromMultipleElementList_THEN_maxIsPopped() {
        underTest.push(this.getSort().parseElement("23"));
        underTest.push(this.getSort().parseElement("7"));
        underTest.push(this.getSort().parseElement("15"));
        int result1 = (int) underTest.pop();
        int result2 = (int) underTest.pop();
        int result3 = (int) underTest.pop();
        assertEquals(23, result1);
        assertEquals(15, result2);
        assertEquals(7, result3);
        underTest.printElements();
        assertEquals("[]", underTest.getElements());
    }

    @Test
    public void GIVEN_quickPushStringAtoZ_WHEN_popFromMultipleElementList_THEN_maxIsPopped() {
        underTest = ProductFactory.getProduct(ProductType.QUICK_POP, DataType.STRING, OrderStrategy.ASCENDING);
        assertNotNull(underTest);
        underTest.push(this.getSort().parseElement("def"));
        underTest.push(this.getSort().parseElement("xyz"));
        underTest.push(this.getSort().parseElement("abc"));
        String result1 = (String) underTest.pop();
        String result2 = (String) underTest.pop();
        String result3 = (String) underTest.pop();
        assertEquals("abc", result1);
        assertEquals("def", result2);
        assertEquals("xyz", result3);
        underTest.printElements();
        assertEquals("[]", underTest.getElements());
    }

    private AbstractComparator<?> getSort() {
        return underTest.getSortAlgorithm();
    }
}
