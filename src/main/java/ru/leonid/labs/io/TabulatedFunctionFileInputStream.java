package ru.leonid.labs.io;

import ru.leonid.labs.function.api.TabulatedFunction;
import ru.leonid.labs.function.factory.ArrayTabulatedFunctionFactory;
import ru.leonid.labs.function.factory.LinkedListTabulatedFunctionFactory;
import ru.leonid.labs.operation.TabulatedDifferentialOperator;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("input/binary function.bin"))) {
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputStream, new ArrayTabulatedFunctionFactory());
            System.out.println("Функция из файла: " + function.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Введите размер и значения функции: ");
        try {
            TabulatedFunction consoleFunction = FunctionsIO.readTabulatedFunction(new BufferedReader(new InputStreamReader(System.in)), new LinkedListTabulatedFunctionFactory());
            TabulatedFunction derivativeFunction = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory()).derive(consoleFunction);
            System.out.println("Производная функции: " + derivativeFunction.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

