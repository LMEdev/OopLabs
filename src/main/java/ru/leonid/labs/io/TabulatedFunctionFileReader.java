package ru.leonid.labs.io;

import ru.leonid.labs.functions.api.TabulatedFunction;
import ru.leonid.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.leonid.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.leonid.labs.functions.impl.ArrayTabulatedFunction;
import ru.leonid.labs.functions.impl.LinkedListTabulatedFunction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        try (
                BufferedReader arrayReader = new BufferedReader(new FileReader("input/function.txt"));
                BufferedReader linkedListReader = new BufferedReader(new FileReader("input/function.txt"))
        ) {
            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(arrayReader, new ArrayTabulatedFunctionFactory());
            System.out.println(arrayFunction);

            TabulatedFunction linkedListFunction = FunctionsIO.readTabulatedFunction(linkedListReader, new LinkedListTabulatedFunctionFactory());
            System.out.println(linkedListFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}