package business;

import domain.Movie;

import java.util.List;

public interface MovieDAO extends DAO<Movie>{

    Movie searchByTitle(String title);
    List<Movie> searchByYear(int year);
    List<Movie> searchByGenre(String genre);
    List<Movie> searchByDuration(int duration);

}
