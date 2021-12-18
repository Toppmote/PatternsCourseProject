package ru.course.builder;

/**
 * Интерфейс строителя
 *
 * @param <E>
 */
public interface Builder<E> {

    /**
     * Получение результатов работы строителя
     *
     * @return построенный объект
     */
    E build();

}
