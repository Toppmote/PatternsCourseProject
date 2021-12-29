package ru.course.strategy;

import ru.course.Config;
import ru.course.systemClasses.FilterResult;
import ru.course.systemClasses.Message;
import ru.course.systemClasses.User;
import ru.course.systemClasses.UserState;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, реализующий лояльный алгоритм фильтрации текста.
 * Блокирует пользователей, когда процент вредоносных слов в сообщении или посте не менее 45.
 */
public class StandardAlgorithm implements Algorithm {

    /**
     * Порог вредоносности текста письма
     */
    public final int HARM_EDGE = 45;

    @Override
    public synchronized FilterResult runAlgorithm(User user, String text) {
        List<String> bad_words = Arrays.stream(text.split(" "))
                .filter(word -> Config.BAD_WORDS.contains(word))
                .collect(Collectors.toList());
        int harmPercent = bad_words.size() / text.split(" ").length * 100;
        if (harmPercent > HARM_EDGE)
            user.setUserState(UserState.BLOCKED_STATE);
        return new FilterResult(user, text, harmPercent);
    }
}
