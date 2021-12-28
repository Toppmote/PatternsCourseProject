package ru.course.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.course.Config;
import ru.course.systemClasses.SystemManager;

/**
 * Контроллер для запросов от клиента
 */
@Slf4j
@RestController
public class FacadeController {

    /**
     * Метод обработки запроса на загрузку начальной страницы
     *
     * @return Model
     */
    @GetMapping("/")
    public ModelAndView loadIndexPage() {
        ModelAndView index = new ModelAndView("index");
        new Config("config.properties");
        SystemManager.getInstance().generateUsers();
        log.info("GET\tLoaded starting page");
        return index;
    }

    /**
     * Метод обработки запроса на нажатие кнопки "Начать"
     *
     * @return Model
     */
    @GetMapping("/main_screen")
    public ModelAndView startSystem() throws InterruptedException {
        SystemManager.getInstance().launchUsersThreads();
        Thread.sleep(500);
        return new ModelAndView("main_page");
    }

}
