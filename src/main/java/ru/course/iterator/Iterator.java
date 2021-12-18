package ru.course.iterator;

/**
 * Интерфейс итератора. Описывает доступ и метод обхода коллекции
 */
public interface Iterator<E> {

    /**
     * Метод, проверяющий, если ли следующий элемент в коллекции
     *
     * @return true - если следующий элемент есть, false - если нет
     */
    boolean hasNext();

    /**
     * Метод, возвращающий следующий элемент коллекции
     *
     * @return следующий элемент коллекции
     */
    E next();

}
