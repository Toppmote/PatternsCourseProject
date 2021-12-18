package ru.course.state;

import ru.course.systemClasses.User;

/**
 * Состояние заблокированного пользователя
 *
 * @see UserState
 */
public class BlockedState extends UserState {

    public BlockedState(User user) {
        super(user);
    }

    @Override
    public void onLoggingIn() {
        System.out.println("You are banned and cannot use our social network.");
    }
}
