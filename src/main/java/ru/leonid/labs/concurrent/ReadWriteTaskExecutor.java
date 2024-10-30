package ru.leonid.labs.concurrent;

import ru.leonid.labs.functions.api.TabulatedFunction;
import ru.leonid.labs.functions.impl.ConstantFunction;
import ru.leonid.labs.functions.impl.LinkedListTabulatedFunction;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);
        ReadTask readTask = new ReadTask(function);
        WriteTask writeTask = new WriteTask(function, 0.5);

        new Thread(readTask).start();
        new Thread(writeTask).start();
    }
}
