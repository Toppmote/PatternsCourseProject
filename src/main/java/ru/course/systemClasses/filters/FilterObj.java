package ru.course.systemClasses.filters;

import lombok.Getter;
import lombok.Setter;
import ru.course.strategy.Algorithm;
import ru.course.systemClasses.FilterResult;
import ru.course.systemClasses.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, реализующий фильтр объектов
 * @see Filter
 */
public class FilterObj implements Filter {

    /**
     * Алгоритм фильтрации
     */
    @Getter
    @Setter
    private Algorithm algorithm;

    /**
     * Список результатов, полученных с помощью данного фильтра
     */
    @Getter
    private final List<FilterResult> results;

    public FilterObj(Algorithm algorithm) {
        System.out.println("Main filter has been initialized");
        this.algorithm = algorithm;
        this.results = new ArrayList<>();
    }

    /**
     * Метод подсчета результата фильтрации для текста, написанного пользователем
     *
     * @param user пользователь
     * @param text текст
     */
    @Override
    public void computeResult(User user, String text) {
        System.out.println("Main filter start working for user " + user.getFIO());
        results.add(algorithm.runAlgorithm(user, text));
        System.out.println("Main filter finished working");
    }

    public List<FilterResult> findFilterResultByUser(User user) {
        return results.stream()
                .filter(filterResult -> filterResult.getUser().equals(user))
                .collect(Collectors.toList());
    }

    public void addFilterResult(FilterResult result) {
        this.results.add(result);
    }

}
