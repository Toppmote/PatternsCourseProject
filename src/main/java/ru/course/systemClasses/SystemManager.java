package ru.course.systemClasses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.course.Config;
import ru.course.iterator.IterableCollection;
import ru.course.iterator.Iterator;
import ru.course.iterator.ListIterator;
import ru.course.systemClasses.filters.Filter;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Класс менеджера системы
 */
public class SystemManager implements IterableCollection {

    private static SystemManager instance;

    /**
     * Логгер
     */
    static Logger logger = LoggerFactory.getLogger(SystemManager.class);

    /**
     * Список пользователей
     */
    private final List<User> userList;

    /**
     * Фильтр, используемый системой
     */
    private Filter filter;

    private SystemManager() {
        this.userList = new ArrayList<>();
        this.filter = null;
        logger.info("System manager has been initialized");
    }

    public static SystemManager getInstance() {
        if (instance == null)
            return new SystemManager();
        return instance;
    }

    /**
     * процедура генерации всех пользователей. Имя и дата регистрации генерируются случайно.
     */
    public void generateUsers() {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        for (int i = 0; i < Config.USERS_QUANTITY; i++) {
            String name = Config.NAMES.get(localRandom.nextInt(Config.NAMES_COUNT)) +
                    " " +
                    Config.SURNAMES.get(localRandom.nextInt(Config.SURNAMES_COUNT));
            String regDate = new Date(localRandom.nextLong(Config.START_DATE.getTime(), new Date().getTime()))
                    .toString();
            this.userList.add(new User(name, regDate));
        }
        System.out.println();
    }

    /**
     * Процедура запуска потоков для каждого клиента.
     */
    public void launchUsersThreads() {
        ExecutorService executorService = Executors.newFixedThreadPool(Config.USERS_QUANTITY);
        for (int i = 0; i < Config.USERS_QUANTITY; i++)
            executorService.execute(this.userList.get(i));
        executorService.shutdown();
    }

    /**
     * Процедура регистрации нового пользователя
     *
     * @param FIO     ФИО
     * @param regDate дата регистрации
     */
    public void newUserRegistration(String FIO, String regDate) {
        User newUser = new User(FIO, regDate);
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
                .filter(currUser -> currUser.getId() == id)
                .findFirst();
    }

    /**
     * Метод поиска результатов фильтрации по ID пользователя
     *
     * @param id ID для поиска
     * @return найденный пользователь
     */
    public List<FilterResult> findFilterResultByUserID(int id) {
        Optional<User> user = this.findUsersByID(id);
        return user.map(value -> filter.findFilterResultByUser(value)).orElse(null);
    }

    /**
     * Метод добавления нового пользователя в список пользователей
     *
     * @param user новый пользователь
     */
    public void addUser(User user) {
        this.userList.add(user);
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public List<User> getUserList() {
        return userList;
    }

}
