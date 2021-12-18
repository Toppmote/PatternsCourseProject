package ru.course.systemClasses.messageContent;

import ru.course.prototype.CloneableObj;

/**
 * Интерфейс, представляющий контент сообщения
 */
public interface MessageContent extends CloneableObj {

    /**
     * Получить хэш-код контента
     *
     * @return хэш код
     */
    int getContentHashCode();

}
