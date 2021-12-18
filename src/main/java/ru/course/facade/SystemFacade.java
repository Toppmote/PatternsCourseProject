package ru.course.facade;

import ru.course.systemClasses.FilterResult;
import ru.course.systemClasses.SystemManager;
import ru.course.systemClasses.User;

import java.util.List;

/**
 * Класс-фасад, применяемый к системному менеджеру
 */
public class SystemFacade {

    /**
     * Поле, хранящее ссылку на объекта-одиночку
     */
    private static SystemFacade instance;

    /**
     * Менеджер системы обнаружения спама
     */
    private final SystemManager manager;

    private SystemFacade(SystemManager manager) {
        this.manager = manager;
    }

    /**
     * Реализвация паттерна-одиночки
     * @param manager системный менеджер
     * @return ссылка на класс SystemFacade
     */
    public static SystemFacade getInstance(SystemManager manager) {
        if (instance == null) {
            instance = new SystemFacade(manager);
        }
        return instance;
    }

    /**
     * Метод поиска пользователей по имени
     *
     * @param FIO ФИО для поиска
     * @return список найденных пользователей
     */
    public List<User> findUsersByFIO(String FIO) {
        List<User> users = manager.findUsersByFIO(FIO);
        if (users.size() != 0) {
            System.out.println("\nFacade found this results:");
            for (int i = 0; i < users.size(); i++)
                System.out.println(i + 1 + ". " + users.get(i));
            return users;
        }
        System.out.println("\nFacade not found results");
        return null;
    }

    /**
     * Метод поиска результатов фильтрации по ID пользователя
     *
     * @param id ID пользователя
     * @return список результатов фильтрации для пользователя
     */
    public List<FilterResult> findFilterResultsByUserID(int id) {
        List<FilterResult> result = manager.findFilterResultByUserID(id);
        if (result.size() != 0) {
            System.out.println("\nFacade found this results:");
            for (int i = 0; i < result.size(); i++)
                System.out.println(i + 1 + ". " + result);
            return result;
        }
        System.out.println("\nFacade not found results");
        return null;
    }

}
