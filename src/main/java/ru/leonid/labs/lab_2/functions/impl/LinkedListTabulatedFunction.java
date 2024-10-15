package ru.leonid.labs.lab_2.functions.impl;

import ru.leonid.labs.lab_2.functions.api.AbstractTabulatedFunction;
import ru.leonid.labs.lab_2.functions.api.Insertable;
import ru.leonid.labs.lab_2.functions.api.MathFunction;
import ru.leonid.labs.lab_2.functions.api.Removable;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {
    private static class Node {
        public Node prev;
        public Node next;
        public double x;
        public double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private Node head;

    private void addNode(double x, double y){
        Node newNode = new Node(x,y);
        if (head == null){ //добавляем в начало
            head = newNode;
            head.prev = head;
            head.next = head;
        } else { //добавляем в конец
            Node last = head.prev;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = head;
            head.prev = newNode;
        }
    }

    private Node getNode(int index) {
        checkIndex(index);

        Node current;
        if (index < count / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = head.prev;
            for (int i = count - 1; i > index; i--) {
                current = current.prev;
            }
        }

        return current;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        super(xValues.length);
        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("xValues.length != yValues.length");
        }
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Array length must be at least 2");
        }

        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        super(count);

        if (count < 2) {
            throw new IllegalArgumentException("Dots count must be at least 2");
        }

        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        double step = (xTo - xFrom) / (count - 1);

        for (int i = 0; i < count; i++) {
            double x = xFrom + i * step;
            addNode(x, source.apply(x));
        }
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node floorNode = getNode(floorIndex);
        return interpolate(x, floorNode.x, floorNode.next.x, floorNode.y, floorNode.next.y);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.x == x) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.y == y) {
                return i;
            }
            current = current.next;
        }
        return -1;

    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < leftBound()) {
            return 0;
        }

        if (x > rightBound()) {
            return getCount() - 1;
        }

        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.x > x) {
                return i - 1;
            }
            current = current.next;
        }

        return getCount() - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return extrapolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return extrapolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    private double extrapolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX);
    }

    @Override
    public void insert(double x, double y) {
        if (head == null){ //если список пустой просто добавляем новый узел
            addNode(x,y);
            count++;
            return;
        }

        Node current = head;
        for (int i = 0; i < count; ++i){
            if (current.x == x){ //если х уже существует обновляем у
                current.y = y;
                return;
            }

            if (current.x > x){ //если найдено метод для вставки
                Node newNode = new Node(x,y);
                if (current == head){ //если
                    newNode.next = head; //новый узел будет ссылаться на голову
                    newNode.prev = head.prev;
                    head.prev.next = newNode; //обновляем предыдущий последний узел чтобы он указывал на новый узел
                    head.prev = newNode; //обновляем голову
                    head = newNode; //новый узел становится головой
                } else { //вставка между узлами
                    newNode.prev = current.prev;
                    newNode.next = current;
                    current.prev.next = newNode; //предыдущий узел относительно current указывает на новый узел
                    current.prev = newNode; //обновляем prev чтобы он указывал на новый узел
                }
                count++;
                return;
            }
            current = current.next;
        }

        addNode(x, y); //если x больше всех значений, добавляем в конец
        count++;
    }

    @Override
    public void remove(int index){
        checkIndex(index);

        if(count == 0){
            throw new IllegalArgumentException("Cannot remove zero list");
        }

        Node current = head;

        for (int i = 0; i < index; ++i){
            current = current.next;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;

        if (current == head) {
            head = current.next;
        }

        current.x = 0;
        current.y = 0;
        count--;

    }

}
