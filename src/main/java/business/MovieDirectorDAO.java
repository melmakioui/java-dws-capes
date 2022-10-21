package business;

import data.pojos.MovieDirectorDTO;
import domain.Director;
import domain.Movie;

import java.util.List;

public interface MovieDirectorDAO {

    List<MovieDirectorDTO> getMovieDirectorList();
    boolean insertMovieDirector(Director director, Movie movie);
}
