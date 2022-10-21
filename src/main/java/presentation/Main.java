package presentation;

import business.DirectorDAOImpl;
import business.MovieDAOImpl;
import business.MovieDirector;
import business.MovieDirectorDAOImpl;
import domain.Director;
import domain.Movie;

public class Main {
    public static void main(String[] args) {

        MovieDirector movieDAO = new MovieDirectorDAOImpl();
        Director director = new Director("test",20);
        Movie movie = new Movie("test",2000,"test",100);
        movieDAO.insertMovieDirector(director,movie);


    }
}