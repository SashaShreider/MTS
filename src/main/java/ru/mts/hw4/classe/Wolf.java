package ru.mts.hw4.classe;

import java.time.LocalDate;

public class Wolf extends Predator {
    public Wolf(String name, double cost, LocalDate birthDate) {
        super("Wolf bread", name, cost, birthDate);
    }

    public Wolf() {
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

}

