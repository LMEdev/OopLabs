package ru.leonid.labs.io;

import ru.leonid.labs.function.api.TabulatedFunction;
import ru.leonid.labs.function.factory.LinkedListTabulatedFunctionFactory;
import ru.leonid.labs.function.impl.LinkedListTabulatedFunction;
import ru.leonid.labs.operation.TabulatedDifferentialOperator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        String filePath = "output/serialized_linked_list_functions.bin";

        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

            TabulatedFunction tabulatedFunction1 = new LinkedListTabulatedFunction(x -> 2 * x + 1, -3, 3, 5);
            TabulatedFunction derivedFunction1 = operator.derive(tabulatedFunction1);
            TabulatedFunction derivedFunction2 = operator.derive(derivedFunction1);

            FunctionsIO.serialize(bufferedOutputStream, tabulatedFunction1);
            FunctionsIO.serialize(bufferedOutputStream, derivedFunction1);
            FunctionsIO.serialize(bufferedOutputStream, derivedFunction2);
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            System.out.println("Десериализованная функция 1:\n" + FunctionsIO.deserialize(bufferedInputStream).toString());
            System.out.println("Десериализованная функция 2:\n" + FunctionsIO.deserialize(bufferedInputStream).toString());
            System.out.println("Десериализованная функция 3:\n" + FunctionsIO.deserialize(bufferedInputStream).toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}