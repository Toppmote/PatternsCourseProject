package ru.course.iterator;

/**
 * Интерфейс получения итератора из коллекции-реализации интерфейса {@link java.util.List}
 */
public interface IterableCollection {

    /**
     * Метод получения итератора
     * @return Итератор коллекции
     */
    Iterator<?> createIterator();

}
