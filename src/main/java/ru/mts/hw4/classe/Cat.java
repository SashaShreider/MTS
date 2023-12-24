package ru.mts.hw4.classe;

import java.time.LocalDate;

public class Cat extends Predator {
    public Cat(String name, double cost, LocalDate birthDate) {
        super("Cat bread", name, cost, birthDate);
    }

    public Cat() {
    }

    @Override
    public String toString() {
        return "Cat{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
