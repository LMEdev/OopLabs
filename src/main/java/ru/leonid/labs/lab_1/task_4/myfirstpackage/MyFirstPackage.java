package ru.leonid.labs.lab_1.task_4.myfirstpackage;

public class MyFirstPackage {
    private int a;
    private int b;

    public MyFirstPackage(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int action() {
        return a + b;
    }
}
