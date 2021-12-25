package ru.course;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
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
     * Список фамилий
     */
    public static List<String> SURNAMES;

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
