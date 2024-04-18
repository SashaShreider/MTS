package ru.mts.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

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
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public Integer getAge() {
        return Period.between(getBirthDate(), LocalDate.now()).getYears();
    }

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
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return Objects.equals(breed, that.breed) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(character, that.character) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, birthDate);
    }
}
