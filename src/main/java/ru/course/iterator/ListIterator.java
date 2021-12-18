package ru.course.iterator;

import java.util.List;

/**
 * Класс, реализующий интерфейс {@link Iterator}. Применяется для коллекций-реализаций интерфейса {@link List}
 *
 * @param <E> итерируемый класс
 */
public class ListIterator<E> implements Iterator<E> {

    /**
     * Исходный список, по которому итерируемся
     */
    List<E> iterableCollection;

    /**
     * Текущий индекс итерации
     */
    int currentIndex;

    public ListIterator(List<E> iterableCollection) {
        this.iterableCollection = iterableCollection;
        this.currentIndex = 0;
        System.out.println("Iterator has been initialized");
    }

    /**
     * @see Iterator
     */
    @Override
    public boolean hasNext() {
        return currentIndex != iterableCollection.size();
    }

    /**
     * @see Iterator
     */
    @Override
    public E next() {
        if (this.hasNext())
            return iterableCollection.get(currentIndex++);
        throw new ArrayIndexOutOfBoundsException();
    }
}
