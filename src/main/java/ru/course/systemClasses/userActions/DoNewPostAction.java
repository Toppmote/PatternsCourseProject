package ru.course.systemClasses.userActions;

import ru.course.systemClasses.User;

/**
 * Класс, описывающий метод добавления новой записи
 */
public class DoNewPostAction extends UserAction {

    public DoNewPostAction(String date) {
        super(date);
    }

    @Override
    public void doAction(User user) {
        System.out.println("New post has been posted. Date " + date);
    }

}
