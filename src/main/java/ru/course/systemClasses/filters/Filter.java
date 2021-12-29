package ru.course.systemClasses.filters;

import ru.course.systemClasses.FilterResult;
import ru.course.systemClasses.User;

import java.util.List;

/**
 * Интерейс, представляющий фильтр. Наследуется от интерфейса-подписчика
 */
public interface Filter {

    /**
     * Метод, возвращающий результат фильтрации в виде объекта {@link ru.course.systemClasses.FilterResult}
     */
    void computeResult(User user, String text);

    /**
     * Метод получения списка результатов фильтрации
     *
     * @return список результатов фильтрации
     */
    List<FilterResult> getResults();

    /**
     * Метод поиска результатов фильтрации по пользователю
     *
     * @return список найденных результатов фильтрации
     */
    List<FilterResult> findFilterResultByUser(User user);

    /**
     * Метод добавления результата фильтрации
     *
     * @param result результат фильтрации
     */
    void addFilterResult(FilterResult result);

}
