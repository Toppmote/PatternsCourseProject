package ru.course.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.course.systemClasses.SystemManager;

/**
 * Контроллер для запросов от клиента
 */
@Slf4j
@RestController
public class FacadeController {

    SystemManager systemManager;

    /**
     * Метод обработки запроса на загрузку начальной страницы
     *
     * @return Model
     */
    @GetMapping("/")
    public ModelAndView loadIndexPage() {
        ModelAndView index = new ModelAndView("index");
        log.info("GET\tLoaded starting page");
        return index;
    }

    /**
     * Метод обработки запроса на нажатие кнопки "Начать"
     *
     * @return Model
     */
    public ModelAndView startSystem() {
        systemManager = new SystemManager();
        systemManager.initUsers();

        return new ModelAndView("index");
    }

}
