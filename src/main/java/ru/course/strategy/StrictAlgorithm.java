package ru.course.strategy;

import ru.course.Config;
import ru.course.systemClasses.FilterResult;
import ru.course.systemClasses.User;
import ru.course.systemClasses.UserState;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, реализующий строгий алгоритм фильтрации.
 * Блокирует пользователя, когда найдет хотя бы одно вредоносное слово в сообщении или посте.
 */
public class StrictAlgorithm implements Algorithm {

    @Override
    public synchronized FilterResult runAlgorithm(User user, String text) {
        int harmPercent = 0;
        List<String> bad_words = Arrays.stream(text.split(" "))
                .filter(word -> Config.BAD_WORDS.contains(word))
                .collect(Collectors.toList());
        if (bad_words.size() != 0) {
            harmPercent = bad_words.size() / text.split(" ").length * 100;
            user.setUserState(UserState.BLOCKED_STATE);
        }
        return new FilterResult(user, text, harmPercent);
    }
}
