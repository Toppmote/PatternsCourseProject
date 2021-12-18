package ru.course.strategy;

import ru.course.systemClasses.FilterResult;
import ru.course.systemClasses.Message;
import ru.course.systemClasses.User;

import java.util.Random;

/**
 * Класс, реализующий алгоритм фильтрации действий
 *
 * @see Algorithm
 */
public class ActionAlgorithm implements Algorithm {

    @Override
    public FilterResult runAlgorithm(User user, Message message) {
        int max = 100;
        Random random = new Random();
        System.out.println("Action algorithm finished work");
        return new FilterResult(user, message, random.nextInt(max + 1));
    }

}
