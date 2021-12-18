package ru.course.prototype;

/**
 * Интерфейс, определяющий метод для создания копий
 */
public interface CloneableObj {

    /**
     * Метод клонирования объекта, наследующего данный интерфейс
     * @return объект-клон
     */
    CloneableObj clone();

}
