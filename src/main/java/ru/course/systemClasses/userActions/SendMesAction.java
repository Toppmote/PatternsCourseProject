package ru.course.systemClasses.userActions;

import lombok.Getter;
import ru.course.systemClasses.User;
import ru.course.systemClasses.UserState;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, описывающий действие отправки сообщения
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
     * Метод отправки сообщения.
     *
     * @param user пользователь, совершивший действие
     */
    @Override
    public void doAction(User user) {
        if (user.getUserState() != UserState.BLOCKED_STATE) {
            this.setDescription("Send message to user ");
            System.out.println("New message has been sent. Date " + date);
        } else
            System.out.println("User " + user.getFIO() + " cannot send messages, because he is blocked.");
    }

}
