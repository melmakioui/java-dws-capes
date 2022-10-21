package business;

import data.DataAccessMovieDirector;
import data.DataAccessMovieDirectorImpl;
import data.pojos.MovieDirectorDTO;
import domain.Director;
import domain.Movie;

import java.util.List;

public class MovieDirectorDAOImpl implements MovieDirectorDAO {

    private DataAccessMovieDirector dataAccessMovieDirector = new DataAccessMovieDirectorImpl();
    @Override
    public List<MovieDirectorDTO> getMovieDirectorList() {
        return this.dataAccessMovieDirector.getMovieDirectorList();
    }

    @Override
    public boolean insertMovieDirector(Director director, Movie movie) {
        return dataAccessMovieDirector.insertMovieDirector(director,movie);
    }
}
