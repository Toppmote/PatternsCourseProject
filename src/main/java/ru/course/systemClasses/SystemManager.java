package ru.course.systemClasses;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.course.Config;
import ru.course.iterator.IterableCollection;
import ru.course.iterator.Iterator;
import ru.course.iterator.ListIterator;
import ru.course.strategy.StandardAlgorithm;
import ru.course.systemClasses.filters.Filter;
import ru.course.systemClasses.filters.FilterObj;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Класс менеджера системы
 */
public final class SystemManager implements IterableCollection {

    private static volatile SystemManager instance;

    /**
     * Логгер
     */
    static Logger logger = LoggerFactory.getLogger(SystemManager.class);

    /**
     * Список пользователей
     */
    @Getter
    private final List<User> userList;

    /**
     * Фильтр, используемый системой
     */
    @Getter
    @Setter
    private Filter filter;

    private SystemManager() {
        this.userList = new ArrayList<>();
        this.filter = new FilterObj(new StandardAlgorithm());
        logger.info("System manager has been initialized");
    }

    /**
     * Реализация паттерна "одниночка" в многопоточной среде
     *
     * @return объект системного менеджера
     */
    public static SystemManager getInstance() {
        SystemManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized (SystemManager.class) {
            if (instance == null)
                instance = new SystemManager();
            return instance;
        }
    }

    /**
     * процедура генерации всех пользователей. Имя и дата регистрации генерируются случайно.
     */
    public void generateUsers() {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        for (int i = 0; i < Config.USERS_QUANTITY; i++) {
            long id = localRandom.nextLong(1000, 10000);
            String name = Config.NAMES.get(localRandom.nextInt(Config.NAMES_COUNT)) +
                    " " +
                    Config.SURNAMES.get(localRandom.nextInt(Config.SURNAMES_COUNT));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            String regDate = simpleDateFormat.format(new Date(localRandom.nextLong(Config.START_DATE.getTime(),
                    new Date().getTime())));
            this.userList.add(new User(id, name, regDate));
        }
        System.out.println();
    }

    /**
     * Процедура запуска потоков для каждого клиента.
     */
    public void launchUsersThreads() {
        ExecutorService executorService = Executors.newFixedThreadPool(Config.USERS_QUANTITY);
        for (User user : this.userList)
            executorService.execute(user);
        executorService.shutdown();
    }

    /**
     * Процедура регистрации нового пользователя
     *
     * @param id      id
     * @param FIO     ФИО
     * @param regDate дата регистрации
     */
    public void newUserRegistration(long id, String FIO, String regDate) {
        User newUser = new User(id, FIO, regDate);
        userList.add(newUser);
        System.out.println("New user has been added");
    }

    /**
     * @see IterableCollection
     */
    @Override
    public Iterator<?> createIterator() {
        return new ListIterator<>(userList);
    }

    /**
     * Метод поиска пользователей по имени
     *
     * @param FIO ФИО для поиска
     * @return список найденных пользователей
     */
    public List<User> findUsersByFIO(String FIO) {
        return userList.stream()
                .filter(user -> user.FIO.equals(FIO))
                .collect(Collectors.toList());
    }

    /**
     * Метод поиска пользователей по имени
     *
     * @param id ID для поиска
     * @return найденный пользователь
     */
    public Optional<User> findUsersByID(int id) {
        return userList.stream()
                .filter(currUser -> currUser.getUserId() == id)
                .findFirst();
    }

    /**
     * Метод поиска результатов фильтрации по ID пользователя
     *
     * @param id ID для поиска
     * @return найденный пользователь
     */
    public List<FilterResult> findFilterResultByUserID(int id) {
        Optional<User> user = instance.findUsersByID(id);
        return user.map(value -> filter.findFilterResultByUser(value)).orElse(null);
    }

}
