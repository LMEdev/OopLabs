package ru.leonid.labs.functions.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.exceptions.ArrayIsNotSortedException;
import ru.leonid.labs.exceptions.DifferentLengthOfArraysException;
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
}
