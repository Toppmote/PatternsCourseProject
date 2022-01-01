package ru.course.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.course.Config;
import ru.course.strategy.StandardAlgorithm;
import ru.course.strategy.StrictAlgorithm;
import ru.course.systemClasses.FilterResult;
import ru.course.systemClasses.SystemManager;
import ru.course.systemClasses.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер для запросов от клиента
 */
@Slf4j
@RestController
public class FacadeController {

    /**
     * Метод обработки запроса на нажатие кнопки "Начать"
     *
     * @return ModelAndView для главного экрана
     */
    @GetMapping("/main_page")
    public ModelAndView startSystem() {
        new Config("config.properties");
        SystemManager manager = SystemManager.getInstance();
        ModelAndView modelAndView = new ModelAndView("main_page");
        modelAndView.addObject("userList", manager.getUserList().stream()
                .sorted(Comparator.comparingLong(User::getUserId)).collect(Collectors.toList()));
        if (manager.getFilter().getAlgorithm() instanceof StandardAlgorithm)
            modelAndView.addObject("currentAlg", "Стандартный алгоритм");
        else
            modelAndView.addObject("currentAlg", "Строгий алгоритм");
        return modelAndView;
    }

    /**
     * Метод обработки запроса на генерацию пользователей
     *
     * @return редирект на главный экран
     * @throws InterruptedException выбрасывается при методе sleep()
     */
    @GetMapping("/main_page/generate_users")
    public ModelAndView generateUsers() throws InterruptedException {
        SystemManager manager = SystemManager.getInstance();
        manager.generateUsers();
        manager.launchUsersThreads();
        return new ModelAndView("redirect:/main_page");
    }

    /**
     * Метод обработки запроса на смену алгоритма
     *
     * @param algorithm наименование нового алгоритма
     * @return редирект на главный экран
     */
    @GetMapping("/main_page/set_algorithm/{algorithm}")
    public ModelAndView setAlgorithm(@PathVariable String algorithm) {
        if (algorithm.equals("standard"))
            SystemManager.getInstance().getFilter().setAlgorithm(new StandardAlgorithm());
        else
            SystemManager.getInstance().getFilter().setAlgorithm(new StrictAlgorithm());
        return new ModelAndView("redirect:/main_page");
    }

    /**
     * Метод обработки запроса на переход на страницу какого-либо пользователя
     *
     * @param id id пользователя
     * @return ModelAndView для страницы пользователя
     */
    @GetMapping("/main_page/user/{id}")
    public ModelAndView showUser(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("user_page");
        SystemManager.getInstance().getUserList()
                .stream().filter(currUser -> currUser.getUserId() == id).findFirst()
                .ifPresent(user -> modelAndView.addObject("user", user));
        modelAndView.addObject("filterResults", SystemManager.getInstance()
                .findFilterResultByUserID(id));
        return modelAndView;
    }

    /**
     * Метод обработки запроса на повторную фильтрацию действий пользователей
     *
     * @return редирект на главный экран
     */
    @GetMapping("/main_page/do_filtration")
    public ModelAndView doFiltration() {
        SystemManager systemManager = SystemManager.getInstance();
        List<FilterResult> filterResultList = new ArrayList<>(systemManager.getFilter().getResults());
        systemManager.getFilter().getResults().clear();
        for (FilterResult filterResult : filterResultList)
            systemManager.getFilter().computeResult(filterResult.getUser(), filterResult.getText());
        return new ModelAndView("redirect:/main_page");
    }

}
