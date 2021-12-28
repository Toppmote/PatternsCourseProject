package ru.course.systemClasses.userActions;

import ru.course.systemClasses.User;

/**
 * Интерфейс, описывающий действие
 */
public interface Action {

    /**
     * Метод совершения действия
     *
     * @param user пользователь, совершающий действие
     */
    void doAction(User user);

}
