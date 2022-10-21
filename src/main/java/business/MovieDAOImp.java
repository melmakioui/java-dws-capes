package business;

import data.DataAccess;
import data.DataAccessMovieImpl;
import dto.MovieDirectorDTO;
import domain.Movie;
import java.util.List;

public class MovieDAOImp implements DAO<Movie> {

    private DataAccess<Movie> dataAccess;

    public MovieDAOImp() {
        this.dataAccess = new DataAccessMovieImpl();
    }

    @Override
    public List<Movie> list() {
        List<Movie> movieList = dataAccess.list();

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
        return false;
    }

    @Override
    public boolean update(Movie movie) {
        return false;
    }

    @Override
    public boolean insert(Movie movie) {
        Movie newMovie = movie;

        if (this.dataAccess.insert(newMovie))
            return true;

        return false;
    }

    @Override
    public boolean delete(Movie movie) {
        return false;
    }

    @Override
    public Movie search(String movie) {
        String movieToSearch = movie;
        Movie found = this.dataAccess.search(movieToSearch);

        if (found == null)
            return new Movie();

        return found;
    }

}
