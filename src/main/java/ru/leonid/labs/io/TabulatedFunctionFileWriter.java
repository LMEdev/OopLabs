package ru.leonid.labs.io;

import ru.leonid.labs.function.api.AbstractTabulatedFunction;
import ru.leonid.labs.function.impl.ArrayTabulatedFunction;
import ru.leonid.labs.function.impl.LinkedListTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) throws IOException {
        try (BufferedWriter arrayWriter = new BufferedWriter(new FileWriter("output/array function.txt"));
             BufferedWriter linkedListWriter = new BufferedWriter(new FileWriter("output/linked list function.txt"))) {

            //y = x
            AbstractTabulatedFunction arrayFunction = new ArrayTabulatedFunction(
                    new double[]{0.0, 1.0, 2.0, 3.0, 4.0},
                    new double[]{0.0, 1.0, 2.0, 3.0, 4.0}
            );

            //y = x^2
            AbstractTabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(
                    new double[]{0.0, 1.0, 2.0, 3.0, 4.0},
                    new double[]{0.0, 1.0, 4.0, 9.0, 16.0}
            );

            FunctionsIO.writeTabulatedFunction(arrayWriter, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListWriter, linkedListFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
