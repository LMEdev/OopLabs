package ru.leonid.labs.functions.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.exceptions.ArrayIsNotSortedException;
import ru.leonid.labs.exceptions.DifferentLengthOfArraysException;
import ru.leonid.labs.functions.impl.ArrayTabulatedFunction;
import ru.leonid.labs.functions.impl.LinkedListTabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование AbstractTabulatedFunction")
class AbstractTabulatedFunctionTest {

    @Test
    void checkLengthEquality() {
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {4.0, 5.0, 6.0};

        assertDoesNotThrow(() -> AbstractTabulatedFunction.checkLengthIsTheSame(xData, yData));

        double[] xDataShort = {1.0, 2.0};
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(xDataShort, yData));

        double[] anotherXDataShort = {2.0, 1.0};
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(anotherXDataShort, yData));
    }

    @Test
    void checkArraySorting() {
        double[] sortedXData = {1.0, 2.0, 3.0, 5.0};
        assertDoesNotThrow(() -> AbstractTabulatedFunction.checkSorted(sortedXData));

        double[] repeatedXData = {1.0, 1.0, 2.0, 3.5};
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(repeatedXData));

        double[] unsortedXData = {1.0, 1.5, 3.0, 2.0};
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(unsortedXData));
    }

    @Test
    void testToString() {
        double[] xValues1 = {1.0, 2.0, 3.0};
        double[] yValues1 = {4.5, 5.5, 6.5};
        LinkedListTabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues1, yValues1);

        double[] xValues2 = {1.0, 2.5, 3.0, 4.0};
        double[] yValues2 = {7.0, 3.5, 0.0, 8.0};
        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues2, yValues2);

        String expectedLinkedListString = "LinkedListTabulatedFunction size = 3\n" +
                "[1.0; 4.5]\n" +
                "[2.0; 5.5]\n" +
                "[3.0; 6.5]\n";

        String expectedArrayString = "ArrayTabulatedFunction size = 4\n" +
                "[1.0; 7.0]\n" +
                "[2.5; 3.5]\n" +
                "[3.0; 0.0]\n" +
                "[4.0; 8.0]\n";

        assertEquals(expectedLinkedListString, linkedListFunction.toString());
        assertEquals(expectedArrayString, arrayFunction.toString());
    }
}
