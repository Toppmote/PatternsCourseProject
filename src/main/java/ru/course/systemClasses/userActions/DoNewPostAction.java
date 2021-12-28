package ru.course.systemClasses.userActions;

import ru.course.systemClasses.User;
import ru.course.systemClasses.UserState;

/**
 * Класс, описывающий метод добавления новой записи
 */
public class DoNewPostAction extends UserAction {

    public DoNewPostAction(String date) {
        super(date);
    }

    /**
     * Текст поста
     */
    public String postMessage;

    /**
     * Создание нового поста пользователем
     *
     * @param user пользователь, совершивший действие
     */
    @Override
    public void doAction(User user) {
        if (user.getUserState() != UserState.BLOCKED_STATE) {
            System.out.println("New post has been posted. Date " + date);
        } else
            System.out.println("User " + user.getFIO() + " cannot add new posts, because he is blocked.");
    }

}
