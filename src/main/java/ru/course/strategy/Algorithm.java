package ru.course.strategy;

import ru.course.systemClasses.FilterResult;
import ru.course.systemClasses.Message;
import ru.course.systemClasses.User;

/**
 * Интерфейс, описывающий алгоритмы фильтрации
 */
public interface Algorithm {

    /**
     * Метод запуска алгоритма
     */
    FilterResult runAlgorithm(User user, Message message);

}
