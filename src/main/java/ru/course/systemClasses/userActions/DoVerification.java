package ru.course.systemClasses.userActions;

import ru.course.systemClasses.User;
import ru.course.systemClasses.UserState;

/**
 * Класс, описывающий действие подтверждение страницы
 */
public class DoVerification extends UserAction {

    public DoVerification(String date) {
        super(date);
    }

    /**
     * Подтверждение страницы пользователем
     *
     * @param user пользователь, совершивший действие
     */
    @Override
    public synchronized void doAction(User user) {
        if (user.getUserState() != UserState.BLOCKED_STATE) {
            System.out.println("User with FIO " + user.getFIO() + " verified his account.");
            user.setUserState(UserState.VERIFIED_STATE);
        } else
            System.out.println("User " + user.getFIO() + " cannot verify his account, because he is blocked.");
    }
}
