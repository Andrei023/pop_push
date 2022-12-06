package eu.heisenbug.product;

import eu.heisenbug.sort.AbstractComparator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductNewTypeTest {

    @Test
    public void GIVEN_newType_WHEN_newProduct_THEN_noCodeChangesNeeded() {

        AbstractOrderedList<Apple> underTest = new QuickPopOrderedList<>(new AppleComparator());

        Apple a1 = new Apple(230, "red");
        Apple a2 = new Apple(169, "yellow");
        Apple a3 = new Apple(169, "red");

        underTest.push(a1);
        underTest.push(a2);
        underTest.push(a3);

        List<Apple> poppedApples = new ArrayList<>();
        poppedApples.add(underTest.pop());
        poppedApples.add(underTest.pop());
        poppedApples.add(underTest.pop());

        assertEquals(169, poppedApples.get(0).getWeight());
        assertEquals(169, poppedApples.get(1).getWeight());
        assertEquals(230, poppedApples.get(2).getWeight());

        assertEquals("red", poppedApples.get(0).getColor());
        assertEquals("yellow", poppedApples.get(1).getColor());
        assertEquals("red", poppedApples.get(2).getColor());
    }

    static class Apple {
        int weight;
        String color;

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    static class AppleComparator extends AbstractComparator<Apple> {

        public AppleComparator() {
            super(Apple.class);
        }

        @Override
        public Object parseElement(String element) {
            return null;
        }

        @Override
        public int compare(Apple a1, Apple a2) {
            assertNotNull(a1);
            assertNotNull(a2);
            String value1 = a1.getWeight() + a1.getColor();
            String value2 = a2.getWeight() + a2.getColor();
            return value2.compareTo(value1);
        }
    }
}
