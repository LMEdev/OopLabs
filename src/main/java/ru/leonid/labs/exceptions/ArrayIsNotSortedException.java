package ru.leonid.labs.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
  public ArrayIsNotSortedException() {
    super();
  }

  public ArrayIsNotSortedException(String message) {
    super(message);
  }
}