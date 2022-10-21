package business;

import domain.Movie;

import java.util.List;

public class MovieDAOImpl implements MovieDAO{


    @Override
    public List<Movie> list() {
        return null;
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public boolean exists(String data) {
        return false;
    }

    @Override
    public boolean update(Movie movie) {
        return false;
    }

    @Override
    public boolean insert(Movie movie) {
        return false;
    }

    @Override
    public boolean delete(Movie movie) {
        return false;
    }

    @Override
    public Movie search(String t) {
        return null;
    }

    @Override
    public Movie searchByTitle(String title) {
        return null;
    }

    @Override
    public List<Movie> searchByYear(int year) {
        return null;
    }

    @Override
    public List<Movie> searchByGenre(String genre) {
        return null;
    }

    @Override
    public List<Movie> searchByDuration(int duration) {
        return null;
    }
}
