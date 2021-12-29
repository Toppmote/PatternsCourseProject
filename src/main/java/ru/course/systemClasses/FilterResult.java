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
     * Текст, написанный пользователем
     */
    private final String text;

    /**
     * Процент вредоносности
     */
    private final int harmPercent;

    public FilterResult(User user, String text, int harmPercent) {
        this.user = user;
        this.text = text;
        this.harmPercent = harmPercent;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "FilterResult{" +
                "author=" + user +
                ", text=" + text +
                ", harmPercent=" + harmPercent +
                '}';
    }
}
