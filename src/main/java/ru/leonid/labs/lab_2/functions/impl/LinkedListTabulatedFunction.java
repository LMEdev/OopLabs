package ru.leonid.labs.lab_2.functions.impl;

import ru.leonid.labs.lab_2.functions.api.AbstractTabulatedFunction;
import ru.leonid.labs.lab_2.functions.api.MathFunction;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private int count;
    private Node head;

    private static class Node {
        private double x;
        private double y;
        private Node next;
        private Node prev;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        super(xValues.length);
        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("xValues.length != yValues.length");
        }
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Array length must be at least 2");
        }

        head = new Node(xValues[0], yValues[0]);
        Node current = head;
        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] <= xValues[i - 1]) {
                throw new IllegalArgumentException("xValues are not sorted in increasing order");
            }
            Node newNode = new Node(xValues[i], yValues[i]);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        current.next = head;
        head.prev = current;

        count = xValues.length;
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        super(count);
        if (count < 2) {
            throw new IllegalArgumentException("Count must be at least 2");
        }
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        double step = (xTo - xFrom) / (count - 1);
        head = new Node(xFrom, source.apply(xFrom));
        Node current = head;

        for (int i = 1; i < count; i++) {
            double x = xFrom + i * step;
            Node newNode = new Node(x, source.apply(x));
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        current.next = head;
        head.prev = current;

        this.count = count;
    }

    private void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node last = head.prev;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = head;
            head.prev = newNode;
        }
        count++;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("index out of bounds " + index);
        }
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
        int index = 0;
        do {
            if (current.x == x) {
                return index;
            }
            index++;
            current = current.next;
        } while (current != head);
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node current = head;
        int index = 0;
        do {
            if (current.y == y) {
                return index;
            }
            index++;
            current = current.next;
        } while (current != head);
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
        Node current = head;
        int index = 0;
        if (x < leftBound()) {
            return 0;
        }
        while (current.next != head && current.next.x <= x) {
            current = current.next;
            index++;
        }
        return index;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return extrapolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        Node last = head.prev;
        return extrapolate(x, last.prev.x, last.x, last.prev.y, last.y);
    }

    private double extrapolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX);
    }
}
