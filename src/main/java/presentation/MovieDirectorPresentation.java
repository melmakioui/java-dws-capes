package presentation;

import business.MovieDAO;
import business.MovieDirectorDAO;
import business.MovieDirectorDAOImpl;
import data.ConnectionDatabase;
import data.pojos.MovieDirectorDTO;
import domain.Director;
import domain.Movie;

import java.util.List;
import java.util.Scanner;

public class MovieDirectorPresentation {

    private static Scanner movieDirectorInput = new Scanner(System.in);
    private static ConnectionDatabase connectionDatabase;
    private static MovieDirectorDAO movieDirectorDAO;

    public static void init(ConnectionDatabase conn){
        connectionDatabase = conn;
        movieDirectorDAO = new MovieDirectorDAOImpl(connectionDatabase);
    }

    public static void listMoviesDirectors(){
        List<MovieDirectorDTO>  movieDirectorDTOS =  movieDirectorDAO.getMovieDirectorList();

        if (movieDirectorDTOS.isEmpty())
            System.out.println("TABLES ARE EMPTY");
        else System.out.println(movieDirectorDTOS);
    }

    public static void insertMovieDirectors(){
        Movie newMovie = MoviePresentation.createNewMovie();
//        Director director = DirectorPresentation.createDirector();

//        movieDirectorDAO.insertMovieDirector(director,newMovie)*****

    }


}
