package data;

import domain.Movie;

import java.util.List;

public interface DataAccessMovie extends DataAccess<Movie> {

    Movie searchByTitle(String title);
    List<Movie> searchByYear(int year);
    List<Movie> searchByGenre(String genre);
    List<Movie> searchByDuration(int duration);
}
