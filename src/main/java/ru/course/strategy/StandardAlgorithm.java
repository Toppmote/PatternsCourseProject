package ru.course.strategy;

import ru.course.Config;
import ru.course.systemClasses.FilterResult;
import ru.course.systemClasses.Message;
import ru.course.systemClasses.User;
import ru.course.systemClasses.UserState;
import ru.course.systemClasses.userActions.DoVerification;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, реализующий лояльный алгоритм фильтрации текста.
 * Блокирует пользователей, когда процент вредоносных слов в сообщении или посте не менее 20.
 */
public class StandardAlgorithm implements Algorithm {

    /**
     * Порог вредоносности текста письма
     */
    public final int HARM_EDGE = 20;

    @Override
    public synchronized FilterResult runAlgorithm(User user, String text) {
        List<String> badWords = Arrays.stream(text.split(" "))
                .filter(word -> Config.BAD_WORDS.contains(word))
                .collect(Collectors.toList());
        int harmPercent = (int) ((double) badWords.size() / Arrays.stream(text.split(" ")).count() * 100);
        if (user.getUserState() != UserState.BLOCKED_STATE) {
            if (harmPercent > HARM_EDGE)
                user.setUserState(UserState.BLOCKED_STATE);
        } else {
            if (user.getActivityFeed().stream().anyMatch(userAction -> userAction instanceof DoVerification))
                user.setUserState(UserState.VERIFIED_STATE);
            else
                user.setUserState(UserState.UNVERIFIED_STATE);
        }
        return new FilterResult(user, text, harmPercent);
    }
}
