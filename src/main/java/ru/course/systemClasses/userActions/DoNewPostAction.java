package ru.course.systemClasses.userActions;

import ru.course.Config;
import ru.course.systemClasses.User;
import ru.course.systemClasses.UserState;

import java.util.concurrent.ThreadLocalRandom;

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
    public synchronized void doAction(User user) {
        if (user.getUserState() != UserState.BLOCKED_STATE) {
            ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
            StringBuilder stringBuilder = new StringBuilder();
            int wordsCount = threadLocalRandom.nextInt(Config.MIN_WORDS_COUNT,Config.MAX_WORDS_COUNT);
            for (int i = 0; i < wordsCount; i++)
                stringBuilder.append(Config.ALL_WORDS.get(threadLocalRandom.nextInt(Config.WORDS_COUNT)))
                        .append(" ");
            this.postMessage = stringBuilder.toString();
            this.description = "New post has been posted.";
        } else
            System.out.println("User " + user.getFIO() + " cannot add new posts, because he is blocked.");
    }

}
