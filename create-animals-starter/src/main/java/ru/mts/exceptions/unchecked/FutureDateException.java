package ru.mts.exceptions.unchecked;

// Исключение, выбрасываемое, если дата рождения меньше текущей даты
public class FutureDateException extends ArgumentException {
    public FutureDateException(String argumentName) {
        super(argumentName, "не может быть больше текущей даты");
    }
}
