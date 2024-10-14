package ru.leonid.labs.lab_1.task_3;

class MyFirstProgram {
    public static void main(String[] s) {
        MySecondClass o = new MySecondClass(1, 2);
        System.out.println(o.action());

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.setA(i);
                o.setB(j);
                System.out.print(o.action() + " ");
            }
            System.out.println();
        }
    }

}

class MySecondClass {
    private int a;
    private int b;

    public MySecondClass(int a, int b) {
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
        return (a * b);
    }
}
