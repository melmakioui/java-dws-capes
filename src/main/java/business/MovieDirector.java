package business;

import data.dtos.MovieDirectorDTO;
import domain.Director;
import domain.Movie;

import java.util.List;

public interface MovieDirector {

    List<MovieDirectorDTO> getMovieDirectorList();
    boolean insertMovieDirector(Director director, Movie movie);
}
