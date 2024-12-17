package ru.leonid.labs.io;

import ru.leonid.labs.function.api.TabulatedFunction;
import ru.leonid.labs.function.factory.ArrayTabulatedFunctionFactory;
import ru.leonid.labs.function.impl.ArrayTabulatedFunction;
import ru.leonid.labs.operation.TabulatedDifferentialOperator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        String filePath = "output/serialized array functions.bin";

        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            double[] xValues = {1.0, 2.0, 3.0, 4.0, 5.0};
            double[] yValues = {2.0, 4.0, 6.0, 8.0, 10.0};

            TabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues);
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());

            TabulatedFunction function2 = operator.derive(function1);
            TabulatedFunction function3 = operator.derive(function2);

            FunctionsIO.serialize(outputStream, function1);
            FunctionsIO.serialize(outputStream, function2);
            FunctionsIO.serialize(outputStream, function3);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            TabulatedFunction deserializedFunction1 = FunctionsIO.deserialize(inputStream);
            TabulatedFunction deserializedFunction2 = FunctionsIO.deserialize(inputStream);
            TabulatedFunction deserializedFunction3 = FunctionsIO.deserialize(inputStream);

            System.out.println("Десериализованная функция 1:\n" + deserializedFunction1);
            System.out.println("Десериализованная функция 2:\n" + deserializedFunction2);
            System.out.println("Десериализованная функция 3:\n" + deserializedFunction3);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
