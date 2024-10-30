package ru.leonid.labs.io;

import ru.leonid.labs.functions.api.AbstractTabulatedFunction;
import ru.leonid.labs.functions.impl.ArrayTabulatedFunction;
import ru.leonid.labs.functions.impl.LinkedListTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        try (BufferedWriter arrayWriter = new BufferedWriter(new FileWriter("output/array function.bin"));
             BufferedWriter linkedListWriter = new BufferedWriter(new FileWriter("output/linked list function.bin"))) {

            //y = x
            double[] xValuesArray = {0.0, 1.0, 2.0, 3.0, 4.0};
            double[] yValuesArray = {0.0, 1.0, 2.0, 3.0, 4.0};
            AbstractTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValuesArray, yValuesArray);

            //y = x^2
            double[] xValuesList = {0.0, 1.0, 2.0, 3.0, 4.0};
            double[] yValuesList = {0.0, 1.0, 4.0, 9.0, 16.0};
            AbstractTabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValuesList, yValuesList);

            FunctionsIO.writeTabulatedFunction(arrayWriter, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListWriter, linkedListFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
