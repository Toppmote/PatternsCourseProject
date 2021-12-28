package ru.course.systemClasses;

import lombok.Getter;
import lombok.Setter;
import ru.course.Config;
import ru.course.systemClasses.userActions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс, описывающий пользователя социальной сети
 */
public class User extends Thread {

    /**
     * ID пользователя. Уникально
     */
    @Getter
    private final long id;

    /**
     * ФИО пользователя
     */
    @Getter
    @Setter
    public String FIO;

    /**
     * Дата регистрации
     */
    @Getter
    private final String regDate;

    /**
     * Список друзей
     */
    @Getter
    public final List<User> friendsList;

    /**
     * Лента активности
     */
    @Getter
    private final List<UserAction> activityFeed;

    /**
     * Список сообщений
     */
    @Getter
    private final List<Message> messageList;

    /**
     * Состояние пользователя
     */
    private UserState state;

    public User(String FIO, String regDate) {
        this.FIO = FIO;
        this.regDate = regDate;
        friendsList = new ArrayList<>();
        activityFeed = new ArrayList<>();
        messageList = new ArrayList<>();
        this.id = this.hashCode();
        this.state = UserState.UNVERIFIED_STATE;
    }

    /**
     * Процедура добавления друга
     *
     * @param friend новый друг
     */
    public void addFriend(User friend) {
        this.friendsList.add(friend);
    }

    /**
     * Процедура удаления друга
     *
     * @param friend удаляемый друг
     */
    public void deleteFriend(User friend) {
        this.friendsList.remove(friend);
    }

    /**
     * Процедура отправки сообщения
     *
     * @param message сообщение
     */
    public void sendMessage(Message message) {
        this.messageList.add(message);
    }

    /**
     * Статическая процедура верификации пользователя
     *
     * @param user пользователь
     */
    public static void doVerification(User user) {
        System.out.println("User with FIO " + user.getFIO() + " verified his account.");
        user.setUserState(UserState.VERIFIED_STATE);
    }

    /**
     * Геттер для состояния
     *
     * @return состояние пользователя
     */
    public UserState getUserState() {
        return state;
    }

    /**
     * Сеттер для состояния
     *
     * @param state новое состояние пользователя
     */
    public void setUserState(UserState state) {
        this.state = state;
    }

    /**
     * Процедура потока-пользователя.
     * Каждый ползователь выполняет случайное количество действий (минимальная и максимальная границы прописаны в
     * проперти файле). Выполненные действия добавляются в ленку активности.
     * Сгенерироваться может одно из четырех действий: добавить нового друга, добавить новый пост, отправить сообщение
     * другому пользователю, подтвердить аккаунт.
     */
    @Override
    public void run() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int userActionQuantity = threadLocalRandom
                .nextInt(Config.MIN_USER_ACTIONS_QUANTITY, Config.MAX_USER_ACTIONS_QUANTITY);
        for (int i = 0; i < userActionQuantity; i++) {
            int actionNumber = threadLocalRandom.nextInt(Config.ACTIONS_QUANTITY);
            switch (actionNumber) {
                case 0:
                    UserAction addNewFriendAction = new AddNewFriendAction(new Date().toString());
                    addNewFriendAction.doAction(this);
                    this.activityFeed.add(addNewFriendAction);
                    break;
                case 1:
                    UserAction doNewPostAction = new DoNewPostAction(new Date().toString());
                    doNewPostAction.doAction(this);
                    this.activityFeed.add(doNewPostAction);
                    break;
                case 2:
                    UserAction sendMesAction = new SendMesAction(new Date().toString());
                    sendMesAction.doAction(this);
                    this.activityFeed.add(sendMesAction);
                    break;
                case 3:
                    if (this.state != UserState.VERIFIED_STATE) {
                        UserAction doVerification = new DoVerification(new Date().toString());
                        doVerification.doAction(this);
                        this.activityFeed.add(doVerification);
                        break;
                    } else
                        System.out.println("User " + this.FIO + " already verify his account");
                default:
                    System.out.println("Something went wrong. Generated action number out of scope.");
                    break;
            }
        }
    }
}
