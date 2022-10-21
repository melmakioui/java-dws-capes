package business;

import data.DataAccessMovieDirector;
import data.DataAccessMovieDirectorImpl;
import data.dtos.MovieDirectorDTO;
import domain.Director;
import domain.Movie;

import java.util.List;

public class MovieDirectorDAOImpl implements MovieDirector{

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
