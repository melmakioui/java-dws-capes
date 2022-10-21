package presentation;

import business.MovieDirectorDAO;
import business.MovieDirectorDAOImpl;
import domain.Director;
import domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MovieDirectorDAO movieDAO = new MovieDirectorDAOImpl();
        Director director = new Director("vass",40);
        Movie movie = new Movie("vasss",2012,"vasss",200);
        movieDAO.insertMovieDirector(director,movie);

    }
}