package ru.course;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, в котором находятся основные константы и методы для их инициализации
 */
public class Config {

    /**
     * Общее количество пользователей в социальной сети
     */
    public static int USERS_QUANTITY;

    /**
     * Общее количество действий, совершаемых каждым пользователем
     */
    public static int USER_ACTIONS_QUANTITY;

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
    public static LocalDate START_DATE;

    public Config(String propertyFileName) {
        initAllData(propertyFileName);
    }

    /**
     * Инициализация всех данных
     */
    public void initAllData(String propertyFileName) {
        Properties properties = initProperties(propertyFileName);
        USERS_QUANTITY = Integer.parseInt(properties.getProperty("users_quantity"));
        USER_ACTIONS_QUANTITY = Integer.parseInt(properties.getProperty("user_actions_quantity"));
        NAMES = Arrays.stream(properties.getProperty("user_names")
                        .replace(", ", "")
                        .split(""))
                .collect(Collectors.toList());
        SURNAMES = Arrays.stream(properties.getProperty("user_surnames")
                        .replace(", ", "")
                        .split(""))
                .collect(Collectors.toList());
        Integer[] intStartDate = (Integer[]) Arrays.stream(properties.getProperty("start_date").split("\\."))
                .map(Integer::parseInt).toArray();
        START_DATE = LocalDate.of(intStartDate[2], intStartDate[1], intStartDate[0]);
    }

    /**
     * Получить свойства из файла
     *
     * @return объект со свойствами
     */
    private Properties initProperties(String propertyFileName) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = Config.class.getResourceAsStream(propertyFileName);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
