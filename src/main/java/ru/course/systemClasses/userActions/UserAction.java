package ru.course.systemClasses.userActions;

import ru.course.systemClasses.User;

/**
 * Абстрактный класс, описывающий действия пользователей
 */
public abstract class UserAction implements Action {

    /**
     * Дата совершения действия
     */
    public final String date;

    public UserAction(String date) {
        this.date = date;
    }

    /**
     * Метод совершения действия
     * @param user пользователь, совершивший действие
     */
    public abstract void doAction(User user);

    public String getDate() {
        return date;
    }

}
