package ru.mts.exceptions.unchecked;

public class ArgumentException extends IllegalArgumentException {
    public ArgumentException(String argumentName, String message) {
        super("Аргумент '" + argumentName + "': " + message);
    }
}

