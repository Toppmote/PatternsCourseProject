package ru.course.state;

import ru.course.systemClasses.User;

/**
 * Абстрактный класс состояния пользователя
 */
public abstract class UserState {

    /**
     * Пользователь
     */
    protected User user;

    public UserState(User user) {
        this.user = user;
    }

    /**
     * Метод, обрабатывающий процедуру авторизации. Для каждого состояния разная обработка
     */
    public abstract void onLoggingIn();

}
