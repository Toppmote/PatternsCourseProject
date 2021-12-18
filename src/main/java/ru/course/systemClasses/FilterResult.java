package ru.course.systemClasses;

/**
 * Класс, описывающий результат фильтрации
 */
public class FilterResult {

    /**
     * Пользователь
     */
    private final User user;

    /**
     * Сообщение
     */
    private final Message message;

    /**
     * Процент вредоносности
     */
    private final int harmPercent;

    public FilterResult(User user, Message message, int harmPercent) {
        this.user = user;
        this.message = message;
        this.harmPercent = harmPercent;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "FilterResult{" +
                "author=" + user +
                ", message=" + message +
                ", harmPercent=" + harmPercent +
                '}';
    }
}
