package ru.mts.exceptions.unchecked;

// Исключение, выбрасываемое, если аргумент равен null
public class NullArgumentException extends ArgumentException {
    public NullArgumentException(String argumentName) {
        super(argumentName, "не может быть null");
    }
}
