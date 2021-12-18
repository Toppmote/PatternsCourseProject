package ru.course.systemClasses.userActions;

import ru.course.systemClasses.User;

/**
 * Класс, описывающий метод добавления нового друга
 */
public class AddNewFriendAction extends UserAction {

    public AddNewFriendAction(String date) {
        super(date);
    }

    @Override
    public void doAction(User user) {
        System.out.println("New friend has been added. Date " + date);
    }

}
