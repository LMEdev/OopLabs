package ru.leonid.labs.concurrent;

import ru.leonid.labs.functions.api.TabulatedFunction;
import ru.leonid.labs.functions.impl.LinkedListTabulatedFunction;
import ru.leonid.labs.functions.impl.UnitFunction;

import java.util.ArrayList;
import java.util.List;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MultiplyingTask task = new MultiplyingTask(function);
            Thread thread = new Thread(task);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(function);
    }
}
