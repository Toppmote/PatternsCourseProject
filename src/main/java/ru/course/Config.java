package ru.course;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс, использующийся для автогенерации пользователей и их действий
 * В нем находятся основные константы и методы для их инициализации из проперти файла.
 */
public class Config {

    /**
     * Общее количество пользователей в социальной сети
     */
    public static int USERS_QUANTITY;

    /**
     * Минимальное количество действий, совершаемых каждым пользователем
     */
    public static int MIN_USER_ACTIONS_QUANTITY;

    /**
     * Максимальное количество действий, совершаемых каждым пользователем
     */
    public static int MAX_USER_ACTIONS_QUANTITY;

    /**
     * Количество возможный действий для пользователей
     */
    public static int ACTIONS_QUANTITY;

    /**
     * Список имен
     */
    public static List<String> NAMES;

    /**
     * Количество имен
     */
    public static int NAMES_COUNT;

    /**
     * Список фамилий
     */
    public static List<String> SURNAMES;

    /**
     * Количество фамилий
     */
    public static int SURNAMES_COUNT;

    /**
     * Начальная дата для регистрации пользователей
     */
    public static Date START_DATE;

    /**
     * Список слов, не приносящих вреда
     */
    public static List<String> GOOD_WORDS;

    /**
     * Список вредоносных слов
     */
    public static List<String> BAD_WORDS;

    /**
     * Список всех слов
     */
    public static List<String> ALL_WORDS;

    /**
     * Общее количество слов
     */
    public static int WORDS_COUNT;

    /**
     * Минимальное количество генерируемых слов
     */
    public static int MIN_WORDS_COUNT;

    /**
     * Максимальное количество генерируемых слов
     */
    public static int MAX_WORDS_COUNT;

    public Config(String propertyFileName) {
        initAllData(propertyFileName);
    }

    /**
     * Инициализация всех данных
     */
    public void initAllData(String propertyFileName) {
        Properties properties = initProperties(propertyFileName);
        USERS_QUANTITY = Integer.parseInt(properties.getProperty("users_quantity"));
        MIN_USER_ACTIONS_QUANTITY = Integer.parseInt(properties.getProperty("min_user_actions_quantity"));
        MAX_USER_ACTIONS_QUANTITY = Integer.parseInt(properties.getProperty("max_user_actions_quantity"));
        ACTIONS_QUANTITY = Integer.parseInt(properties.getProperty("actions_quantity"));
        NAMES = propertyStringToArray(properties, "user_names");
        NAMES_COUNT = NAMES.size();
        SURNAMES = propertyStringToArray(properties, "user_surnames");
        SURNAMES_COUNT = SURNAMES.size();
        String date = properties.getProperty("start_date");
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            START_DATE = dateFormat.parse(date);
        } catch (ParseException e) {
            START_DATE = new Date();
        }
        GOOD_WORDS = propertyStringToArray(properties, "good_words");
        BAD_WORDS = propertyStringToArray(properties, "bad_words");
        ALL_WORDS = Stream.concat(GOOD_WORDS.stream(), BAD_WORDS.stream())
                .collect(Collectors.toList());
        WORDS_COUNT = ALL_WORDS.size();
        MIN_WORDS_COUNT = Integer.parseInt(properties.getProperty("min_words_count"));
        MAX_WORDS_COUNT = Integer.parseInt(properties.getProperty("max_words_count"));
    }

    /**
     * Получить свойства из файла
     *
     * @return объект со свойствами
     */
    private Properties initProperties(String propertyFileName) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * Метод парсинга строки из объекта свойств в список String
     *
     * @param properties       объект свойств
     * @param propertyFileName наименование свойств
     * @return список String
     */
    private List<String> propertyStringToArray(Properties properties, String propertyFileName) {
        return Arrays.stream(properties.getProperty(propertyFileName)
                        .replace(",", "")
                        .split(" "))
                .collect(Collectors.toList());
    }

}
