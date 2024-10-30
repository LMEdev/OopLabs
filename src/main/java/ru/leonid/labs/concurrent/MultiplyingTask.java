package ru.leonid.labs.concurrent;

import ru.leonid.labs.functions.api.TabulatedFunction;

public class MultiplyingTask implements Runnable{
    private final TabulatedFunction function;

    public MultiplyingTask(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void run() {
        for (int i = 0; i < function.getCount(); i++) {
            synchronized (function) {
                double currentY = function.getY(i);
                function.setY(i, currentY * 2);
            }
        }
        System.out.printf("Thread %s has completed the task.%n", Thread.currentThread().getName());
    }
}
