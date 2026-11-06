package ru.netology.javaqa_object_dependency_mockito;


public class PosterManager {
    // Создаём поля: для хранения фильмов и переменную для хранения значения лимита вывода последних фильмов
    private String[] movies = new String[0];
    private int limit;

    // Конструктор со значением лимита по-умолчанию равное 6
    public PosterManager() {
        this.limit = 6;
    }

    // Конструктор с возможностью выставить лимит при создании менеджера
    public PosterManager(int limit) {
        this.limit = limit;
    }

    // Метод добавления нового фильма в последнюю ячейку и новый массив
    public void save(String movie) {
        String[] tmp = new String[movies.length + 1];
        for (int i = 0; i < movies.length; i++) {
            tmp[i] = movies[i];
        }
        tmp[tmp.length - 1] = movie;
        this.movies = tmp;
    }

    // Метод вывода фильмов в порядке их добавления
    public String[] findAll() {
        return movies;
    }

    // Метод вывода последних добавленных фильмов в обратном порядке с вычислением
    // правильного ожидаемого размера результирующего массива-ответа
    public String[] findLast() {
        int resultLength;
        if (movies.length > limit) {
            resultLength = limit; // Если фильмов больше - берем только лимит
        } else {
            resultLength = movies.length; // Если фильмов меньше - берем количество
            // в массиве movies
        }

        String[] result = new String[resultLength];

        // Заполняем поле в обратном порядке
        for (int i = 0; i < resultLength; i++) {
            result[i] = movies[movies.length - 1 - i];
        }

        return result;
    }
}

