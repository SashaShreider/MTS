package ru.mts.hw4;

import java.math.BigDecimal;

public interface Animal {
    /**
     * Возвращает породу животного.
     */
    String getBreed();

    /**
     * Возвращает породу животного.
     */
    String getName();

    /**
     * Возвращает цену животного.
     */
    BigDecimal getCost();

    /**
     * Возвращает характер животного.
     */
    String getCharacter();
}
