package ru.course.systemClasses.userActions;

import lombok.Getter;
import lombok.Setter;
import ru.course.systemClasses.User;

/**
 * Абстрактный класс, описывающий действия пользователей
 */
@Getter
public abstract class UserAction implements Action {

    /**
     * Дата совершения действия
     */
    public final String date;

    /**
     * Описание действия
     */
    @Setter
    public String description;

    public UserAction(String date) {
        this.date = date;
    }

    /**
     * Метод совершения действия
     * @param user пользователь, совершивший действие
     */
    public abstract void doAction(User user);

}
