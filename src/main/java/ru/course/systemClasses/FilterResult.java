package ru.course.systemClasses;

import lombok.Getter;

/**
 * Класс, описывающий результат фильтрации
 */
public class FilterResult {

    /**
     * Пользователь
     */
    @Getter
    private final User user;

    /**
     * Текст, написанный пользователем
     */
    @Getter
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

    @Override
    public String toString() {
        return "FilterResult{" +
                "author=" + user +
                ", text=" + text +
                ", harmPercent=" + harmPercent +
                '}';
    }
}
