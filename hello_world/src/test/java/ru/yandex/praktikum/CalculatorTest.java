package ru.yandex.praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CalculatorTest {
    private final int firstItem;
    private final int secondItem;
    private final int expected;

    public CalculatorTest(int firstItem, int secondItem, int expected) {
        this.firstItem = firstItem;
        this.secondItem = secondItem;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][] {
                {5, 6, 11},
                {55, 0, 55},
                {-10, 10, 0}
        };
    }

    @Test
    public void shouldDoSum() {
        Calculator calc = new Calculator();
        int actual = calc.sum(firstItem, secondItem);
        assertEquals(expected, actual);
    }
}
