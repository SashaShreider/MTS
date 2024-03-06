package ru.mts.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public abstract class AbstractAnimal implements Animal, Cloneable {
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
    protected LocalDate birthDate;

    public AbstractAnimal() {
    }

    protected AbstractAnimal(String breed, String name, double cost, LocalDate birthDate, String character) {
        this.breed = breed;
        this.name = name;
        this.cost = new BigDecimal(cost).setScale(2, RoundingMode.HALF_UP);
        this.birthDate = birthDate;
        this.character = character;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate;}

    @Override
    public AbstractAnimal clone() {
        try {
            return (AbstractAnimal) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
