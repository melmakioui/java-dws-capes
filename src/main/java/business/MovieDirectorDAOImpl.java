package business;

import data.config.ConnectionDatabase;
import data.DataAccessMovieDirector;
import data.DataAccessMovieDirectorImpl;
import data.pojos.MovieDirectorDTO;
import domain.Director;
import domain.Movie;

import java.util.List;

public class MovieDirectorDAOImpl implements MovieDirectorDAO {

    private DataAccessMovieDirector dataAccessMovieDirector;
    private ConnectionDatabase connectionDatabase;

    public MovieDirectorDAOImpl(ConnectionDatabase connectionDatabase) {
        this.connectionDatabase = connectionDatabase;
        this.dataAccessMovieDirector = new DataAccessMovieDirectorImpl(this.connectionDatabase);
    }

    @Override
    public List<MovieDirectorDTO> getMovieDirectorList() {
        return this.dataAccessMovieDirector.getMovieDirectorList();
    }

    @Override
    public boolean insertMovieDirector(int movieId, int directorId) {
        return dataAccessMovieDirector.insertMovieDirector(movieId,directorId);
    }
}
