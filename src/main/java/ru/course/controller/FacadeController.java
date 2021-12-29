package ru.course.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.course.Config;
import ru.course.strategy.StandardAlgorithm;
import ru.course.strategy.StrictAlgorithm;
import ru.course.systemClasses.SystemManager;

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
    @GetMapping("/main_screen")
    public ModelAndView startSystem()  {
        new Config("config.properties");
        SystemManager manager = SystemManager.getInstance();
        ModelAndView modelAndView = new ModelAndView("main_page");
        modelAndView.addObject("userList", manager.getUserList());
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
    @GetMapping("/main_screen/generate_users")
    public ModelAndView generateUsers() throws InterruptedException {
        SystemManager manager = SystemManager.getInstance();
        manager.generateUsers();
        manager.launchUsersThreads();
        Thread.sleep(1000);
        return new ModelAndView("redirect:/main_screen");
    }

    /**
     * Метод обработки запроса на смену алгоритма
     *
     * @param algorithm наименование нового алгоритма
     * @return редирект на главный экран
     */
    @GetMapping("/main_screen/set_algorithm/{algorithm}")
    public ModelAndView setAlgorithm(@PathVariable String algorithm) {
        if (algorithm.equals("standard"))
            SystemManager.getInstance().getFilter().setAlgorithm(new StandardAlgorithm());
        else
            SystemManager.getInstance().getFilter().setAlgorithm(new StrictAlgorithm());
        return new ModelAndView("redirect:/main_screen");
    }

    /**
     * Метод обработки запроса на переход на страницу какого-либо пользователя
     *
     * @param id id пользователя
     * @return ModelAndView для страницы пользователя
     */
    @GetMapping("/main_screen/user/{id}")
    public ModelAndView showUser(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("user_page");
        modelAndView.addObject("user", SystemManager.getInstance().getUserList().get(id - 1));
        modelAndView.addObject("filterResults", SystemManager.getInstance()
                .findFilterResultByUserID(id - 1));
        return modelAndView;
    }

}
