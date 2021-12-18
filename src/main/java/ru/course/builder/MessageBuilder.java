package ru.course.builder;

import ru.course.systemClasses.Message;
import ru.course.systemClasses.User;
import ru.course.systemClasses.messageContent.MessageContent;

import java.util.List;

/**
 * Класс строителя сообщений
 */
public class MessageBuilder implements Builder<Message> {

    private final Message message;

    public MessageBuilder() {
        this.message = new Message();
    }

    /**
     * Задать сообщению отправителя
     *
     * @param sender отправидель
     * @return этот объект класса строителя
     */
    public MessageBuilder sender(User sender) {
        message.setSender(sender);
        return this;
    }

    /**
     * Задать сообщению список получателей
     *
     * @param recipientList список получателей
     * @return этот объект класса строителя
     */
    public MessageBuilder recipientList(List<User> recipientList) {
        message.setRecipientList(recipientList);
        return this;
    }

    /**
     * Задать сообщению список содержимого
     *
     * @param messageContent список содержимого
     * @return этот объект класса строителя
     */
    public MessageBuilder contentList(List<MessageContent> messageContent) {
        message.setMessageContent(messageContent);
        return this;
    }

    /**
     * Метод построения сообщения
     *
     * @return объект сообщения
     */
    @Override
    public Message build() {
        return message;
    }
}
