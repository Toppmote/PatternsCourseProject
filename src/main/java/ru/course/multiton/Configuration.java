package ru.course.multiton;

import java.util.HashMap;

/**
 * Класс конфигурации системы. Является классом-мультитоном.
 */
public final class Configuration {

    /**
     * Словарь, хранящий все конфигурации
     */
    private static final HashMap<ConfigurationType, Configuration> configurations;

    /**
     * Тип конфигурации
     */
    private final ConfigurationType configurationType;

    static {
        configurations = new HashMap<>();
    }

    private Configuration(ConfigurationType configurationType) {
        this.configurationType = configurationType;
    }

    /**
     * Получение объекта из словаря. Если объекта не существует, создаем его и возвращаем
     *
     * @param type тип конфигурации
     * @return соответствующая конфигурация
     */
    public static Configuration getInstance(ConfigurationType type) {
        if (!configurations.containsKey(type))
            configurations.put(type, new Configuration(type));
        return configurations.get(type);
    }

    public ConfigurationType getConfigurationType() {
        return configurationType;
    }
}
