package business;

import data.config.ConnectionDatabase;
import data.DataAccessMovie;
import data.DataAccessMovieImpl;
import domain.Movie;

import java.util.List;

public class MovieDAOImpl implements MovieDAO{

    private DataAccessMovie dataAccessMovie;
    private ConnectionDatabase connectionDatabase;

    public MovieDAOImpl(ConnectionDatabase connectionDatabase) {
        this.connectionDatabase = connectionDatabase;
        this.dataAccessMovie = new DataAccessMovieImpl(this.connectionDatabase);
    }

    @Override
    public List<Movie> list() {
        List<Movie> movieList = dataAccessMovie.list();

        if (movieList.isEmpty())
            System.out.println("EMPTY");

        return movieList;
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public boolean exists(String data) {
        return dataAccessMovie.exists(data);
    }

    @Override
    public boolean update(Movie movie) {
        return dataAccessMovie.update(movie);
    }

    @Override
    public boolean insert(Movie movie) {
        return this.dataAccessMovie.insert(movie);
    }

    @Override
    public boolean delete(Movie movie) {
        return dataAccessMovie.delete(movie);
    }

    @Override
    public Movie search(String title) {
        return dataAccessMovie.search(title);
    }

    @Override
    public Movie searchByTitle(String title) {
        Movie movie = dataAccessMovie.searchByTitle(title);

        return movie;
    }

    @Override
    public List<Movie> searchByYear(int year) {
        List<Movie> movieList = dataAccessMovie.searchByYear(year);
        return movieList;
    }

    @Override
    public List<Movie> searchByGenre(String genre) {
        List<Movie> movieList = dataAccessMovie.searchByGenre(genre);
        return movieList;
    }

    @Override
    public List<Movie> searchByDuration(int duration) {
        List<Movie> movieList = dataAccessMovie.searchByDuration(duration);
        return movieList;
    }
}
