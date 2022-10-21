package presentation;

import business.MovieDAOImp;
import domain.Movie;

public class Main {
    public static void main(String[] args) {

        MovieDAOImp movieDAOImp = new MovieDAOImp();
        System.out.println(movieDAOImp.getMovieWithDirector());

        Movie movie = new Movie("a",2000,"te",12);

        boolean x = movieDAOImp.insert(movie);
        if (x) System.out.println("success");
    }
}