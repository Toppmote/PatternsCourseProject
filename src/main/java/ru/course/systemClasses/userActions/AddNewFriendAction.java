package ru.course.systemClasses.userActions;

import ru.course.Config;
import ru.course.systemClasses.SystemManager;
import ru.course.systemClasses.User;
import ru.course.systemClasses.UserState;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс, описывающий действие добавления нового друга
 */
public class AddNewFriendAction extends UserAction {

    public AddNewFriendAction(String date) {
        super(date);
    }

    /**
     * Метод добавления нового друга
     *
     * @param user пользователь, совершивший действие
     */
    @Override
    public synchronized void doAction(User user) {
        if (user.getUserState() != UserState.BLOCKED_STATE) {
            ThreadLocalRandom localRandom = ThreadLocalRandom.current();
            while (true) {
                User friend = SystemManager.getInstance().getUserList().get(localRandom.nextInt(Config.USERS_QUANTITY));
                if (user != friend) {
                    user.getFriendsList().add(friend);
                    this.setDescription(user.getFIO() + " added new friend " + friend.getFIO());
                    break;
                }
            }
        } else
            System.out.println("User " + user.getFIO() + " cannot add new friends, because he is blocked.");
    }

}
