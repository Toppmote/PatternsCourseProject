package ru.course.systemClasses.userActions;

import lombok.Getter;
import ru.course.Config;
import ru.course.systemClasses.Message;
import ru.course.systemClasses.SystemManager;
import ru.course.systemClasses.User;
import ru.course.systemClasses.UserState;
import ru.course.systemClasses.messageContent.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Класс, описывающий действие отправки сообщенияв
 */
public class SendMesAction extends UserAction {

    /**
     * Список пользователей, которым было отправлено сообщение
     */
    @Getter
    public List<User> recipientList;

    public SendMesAction(String date, User... recipientList) {
        super(date);
        this.recipientList = Arrays.stream(recipientList).collect(Collectors.toList());
    }

    /**
     * Метод отправки сообщения. После отправки сообщения вызываем метод фильтрации текста.
     *
     * @param user пользователь, совершивший действие
     */
    @Override
    public synchronized void doAction(User user) {
        if (user.getUserState() != UserState.BLOCKED_STATE) {
            ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
            StringBuilder stringBuilder = new StringBuilder();
            int friendsCount = user.getFriendsList().size();
            if (friendsCount == 0) {
                user.addFriend();
                friendsCount++;
            }
            int recipientsCount = threadLocalRandom.nextInt(1, friendsCount + 1);
            int wordsCount = threadLocalRandom.nextInt(Config.MIN_WORDS_COUNT, Config.MAX_WORDS_COUNT);
            for (int i = 0; i < wordsCount; i++)
                stringBuilder.append(Config.ALL_WORDS.get(threadLocalRandom.nextInt(Config.WORDS_COUNT)))
                        .append(" ");
            Text messageText = new Text(stringBuilder.toString());
            List<User> recipientList = user.getFriendsList().subList(0, recipientsCount);
            user.getMessageList().add(Message.builder()
                    .sender(user)
                    .recipientList(recipientList)
                    .contentList(Collections.singletonList(messageText))
                    .build());
            this.recipientList = recipientList;
            this.setDescription("Пользователь отправил новое сообщение.</br>Текст сообщения: " + messageText.getTextValue() +
                    "</br>Получатели: " + this.getRecipients(recipientList));
            SystemManager.getInstance().getFilter().computeResult(user, messageText.getTextValue());
            System.out.println("New message has been sent. Date " + date);
            user.getActivityFeed().add(this);
        } else
            System.out.println("User " + user.getFIO() + " cannot send messages, because he is blocked.");
    }

    /**
     * Метод преобразования списка получателей в строку
     *
     * @param recipientList список получателей
     * @return строка со списком получателей
     */
    private synchronized String getRecipients(List<User> recipientList) {
        StringBuilder recipients = new StringBuilder();
        for (User recipient : recipientList)
            recipients.append(recipient.FIO).append(", ");
        recipients.setLength(recipients.length() - 2);
        return recipients.toString();
    }

}
