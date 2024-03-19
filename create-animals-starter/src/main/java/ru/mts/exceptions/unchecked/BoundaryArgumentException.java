package ru.mts.exceptions.unchecked;

// Исключение, выбрасываемое, если аргумент меньше нуля
public class BoundaryArgumentException extends ArgumentException {
    public BoundaryArgumentException(String argumentName, String message) {
        super(argumentName, message);
    }
}
