package ru.leonid.labs.lab_1.task_4;

import ru.leonid.labs.lab_1.task_4.myfirstpackage.MyFirstPackage;

class MyFirstProgram {
    public static void main(String[] s) {
        MyFirstPackage o = new MyFirstPackage(1, 2);
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

