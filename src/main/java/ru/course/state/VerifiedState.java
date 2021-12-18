package ru.course.state;

import ru.course.systemClasses.User;

/**
 * Состояние подтвержденого пользователя
 *
 * @see UserState
 */
public class VerifiedState extends UserState {

    public VerifiedState(User user) {
        super(user);
    }

    @Override
    public void onLoggingIn() {
        System.out.println("Welcome back, " + user.getFIO() + ".");
    }
}
