package ru.course.builder;

import ru.course.systemClasses.Message;
import ru.course.systemClasses.User;
import ru.course.systemClasses.messageContent.Text;

import java.util.Collections;
import java.util.List;

/**
 * Класс директора, определяющего создание той или иной конфигурации сообщения
 */
public class MessageDirector {

    /**
     * Объект строителя сообщения
     */
    private final MessageBuilder builder;

    public MessageDirector() {
        this.builder = new MessageBuilder();
    }

    /**
     * Метод создания пустого сообщения
     *
     * @param sender        отправитель
     * @param recipientList список получаетелей
     * @return пустое письмо
     */
    public Message createEmptyMessage(User sender, List<User> recipientList) {
        return builder
                .sender(sender)
                .recipientList(recipientList)
                .build();
    }

    /**
     * Метод создания текстового сообщения
     *
     * @param sender        отправитель
     * @param recipientList список получаетелей
     * @param text          текст сообщения
     * @return текстовое сообщение
     */
    public Message createTextMessage(User sender, List<User> recipientList, Text text) {
        return builder
                .sender(sender)
                .recipientList(recipientList)
                .contentList(Collections.singletonList(text))
                .build();
    }

}
