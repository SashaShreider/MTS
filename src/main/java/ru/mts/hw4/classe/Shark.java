package ru.mts.hw4.classe;

import java.time.LocalDate;

public class Shark extends Predator {
    public Shark(String name, double cost, LocalDate birthDate) {
        super("Shark bread", name, cost, birthDate);
    }

    public Shark() {
    }

    @Override
    public String toString() {
        return "Shark{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}


