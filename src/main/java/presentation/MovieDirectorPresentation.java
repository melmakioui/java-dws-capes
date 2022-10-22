package presentation;

import business.*;
import data.config.ConnectionDatabase;
import data.pojos.MovieDirectorDTO;
import domain.Director;
import domain.Movie;
import java.util.List;
import java.util.Scanner;

public class MovieDirectorPresentation {

    private static Scanner movieDirectorInput = new Scanner(System.in);
    private static DirectorDAO directorDAO;
    private static MovieDAO movieDAO;
    private static ConnectionDatabase connectionDatabase;
    private static MovieDirectorDAO movieDirectorDAO;

    public static void init(ConnectionDatabase conn){
        connectionDatabase = conn;
        movieDirectorDAO = new MovieDirectorDAOImpl(connectionDatabase);
        directorDAO = new DirectorDAOImpl(connectionDatabase);
        movieDAO = new MovieDAOImpl(connectionDatabase);
    }

    public static void listMoviesDirectors(){
        List<MovieDirectorDTO>  movieDirectorDTOS =  movieDirectorDAO.getMovieDirectorList();

        if (movieDirectorDTOS.isEmpty())
            System.out.println("TABLES ARE EMPTY");
        else System.out.println(movieDirectorDTOS);
    }

    public static void associateMovieToDirector(){

        int movieId = getMovieId();
        int directorId = getDirectorId();

        if (movieId == 0 || directorId == 0){
            System.out.println("NO DIRECTOR OR MOVIE FOUND.");
            return;
        }

        boolean success = movieDirectorDAO.insertMovieDirector(movieId,directorId);

        if (success)
            System.out.println("MOVIE ASSOCIATED WITH DIRECTOR SUCCESSFUL!");
    }

    private static int getMovieId(){

        List<Movie> movieList = movieDAO.list();
        System.out.println(movieList);
        System.out.println("SELECT A MOVIE THAT YOU WANT TO ASSOCIATE WITH DIRECTOR.");
        System.out.print("> ");
        int assocMovie = movieDirectorInput.nextInt();

        for (Movie movie: movieList) {
            if (assocMovie == movie.getId())
                return movie.getId();
        }
        return 0;
    }


    private static int getDirectorId(){

        List<Director> directorList = directorDAO.list();
        System.out.println(directorList);
        System.out.println("SELECT A DIRECTOR.");
        System.out.print("> ");
        int assocDirector = movieDirectorInput.nextInt();

        for (Director director: directorList) {
            if (assocDirector == director.getId())
                return director.getId();
        }
        return 0;
    }


}
