package ru.mts.hw4.classe;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Animal {
    /**
     * Возвращает породу животного.
     *
     * @return порода
     */
    String getBreed();

    /**
     * Возвращает имя животного.
     *
     * @return имя
     */
    String getName();

    /**
     * Возвращает цену животного.
     *
     * @return цена
     */
    BigDecimal getCost();

    /**
     * Возвращает характер животного.
     *
     * @return характер
     */
    String getCharacter();

    /**
     * Возвращает день рождения.
     *
     * @return день рождения
     */
    LocalDate getBirthDate();
}
