package ru.mts.hw4.classe;

import java.time.LocalDate;

public class Dog extends Pet {
    public Dog(String name, double cost, LocalDate birthDate) {
        super("Dog bread", name, cost, birthDate);
    }

    public Dog() {
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

