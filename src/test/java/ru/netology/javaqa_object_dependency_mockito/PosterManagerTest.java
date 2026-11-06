package ru.netology.javaqa_object_dependency_mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PosterManagerTest {


    // Вывод фильмов если менеджер пуст
    @Test
    public void shouldFindAllWhenEmpty() {
        PosterManager manager = new PosterManager();

        String[] expected = new String[]{};
        String[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Вывод фильмов в порядке добавления
    @Test
    public void shouldAddAndFindAllMovies() {
        PosterManager manager = new PosterManager();
        manager.save("Бладшот");
        manager.save("Вперёд");
        manager.save("Отель Белград");

        String[] expected = new String[]{"Бладшот", "Вперёд", "Отель Белград"};
        String[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Вывод в обратном порядке фильмов меньше лимита по умолчанию
    @Test
    public void findLastWithMoviesUnderDefaultLimit() {
        PosterManager manager = new PosterManager();
        manager.save("Бладшот");
        manager.save("Вперёд");
        manager.save("Отель Белград");

        String[] expected = new String[]{"Отель Белград", "Вперёд", "Бладшот"};
        String[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Вывод в обратном порядке ровно лимита при превышении количества фильмов
    @Test
    public void findLastWithMoviesOverDefaultLimit() {
        PosterManager manager = new PosterManager();
        manager.save("Бладшот");
        manager.save("Вперёд");
        manager.save("Отель Белград");
        manager.save("Джентльмены");
        manager.save("Человек-невидимка");
        manager.save("Тролли. Мировой тур");
        manager.save("Номер один");

        String[] expected = new String[]{"Номер один", "Тролли. Мировой тур", "Человек-невидимка", "Джентльмены", "Отель Белград", "Вперёд"};
        String[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Вывод в обратном порядке фильмов по количеству лимита
    @Test
    public void findLastWithMoviesEqualDefaultLimit() {
        PosterManager manager = new PosterManager(4);
        manager.save("Бладшот");
        manager.save("Вперёд");
        manager.save("Отель Белград");
        manager.save("Джентльмены");

        String[] expected = new String[]{"Джентльмены", "Отель Белград", "Вперёд", "Бладшот"};
        String[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Вывод в обратном порядке точно по значению лимита при превышении выбранного количества
    @Test
    public void findLastWithMoviesOverCustomLimit() {
        PosterManager manager = new PosterManager(3); // Лимит 3
        manager.save("Бладшот");
        manager.save("Вперёд");
        manager.save("Отель Белград");
        manager.save("Джентльмены");

        String[] expected = new String[]{"Джентльмены", "Отель Белград", "Вперёд"};
        String[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Вывод в обратном порядке точно по указанному количеству
    @Test
    public void findLastWithMoviesUnderCustomLimit() {
        PosterManager manager = new PosterManager(5); // Лимит 5
        manager.save("Бладшот");
        manager.save("Вперёд");
        manager.save("Отель Белград");

        String[] expected = new String[]{"Отель Белград", "Вперёд", "Бладшот"};
        String[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

}
