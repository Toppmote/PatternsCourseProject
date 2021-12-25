package ru.course;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
    }

    /**
     * Получить свойства из файла
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
