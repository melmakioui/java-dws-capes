package presentation;

import business.MovieDAO;
import business.MovieDAOImpl;
import data.ConnectionDatabase;
import domain.Movie;

import java.util.List;
import java.util.Scanner;

public class MoviePresentation {

    private static Scanner movieInput = new Scanner(System.in);
    private static ConnectionDatabase connectionDatabase;
    private static MovieDAO movieDAO;

    public static void initMoviePresentation(ConnectionDatabase conn){
        connectionDatabase = conn;
        movieDAO = new MovieDAOImpl(connectionDatabase);
    }

    public static void listMovies(){
        List<Movie> movieList = movieDAO.list();

       if (movieList.isEmpty())
           System.out.println("NO MOVIES.");
       else System.out.println(movieList);
    }

    public static void exists(){
        System.out.println("TYPE THE MOVIE TITLE");
        System.out.print("> ");
        String movieExists = movieInput.nextLine();

        if (movieDAO.exists(movieExists))
            System.out.println(movieDAO.searchByTitle(movieExists));
        else System.out.println("MOVIE DOES NOT EXIST");
    }

    public static void update(){
        listMovies();
        System.out.println("SELECT MOVIE TO UPDATE (ID)");
        System.out.print(">");
        int movieUpdate = movieInput.nextInt();

        Movie updateMovie = null;

        for (Movie movie : movieDAO.list())
            if (movie.getId() == movieUpdate)
                updateMovie = movie;

        showUpdateMovieOptions();
        int fieldToUpdate = movieInput.nextInt();
        updateMovie(updateMovie,fieldToUpdate);

        if (movieDAO.update(updateMovie))
            System.out.println("UPDATED");
    }

    private static void showUpdateMovieOptions(){
        System.out.println("*UPDATING MOVIE*");
        System.out.println("1. UPDATE TITLE");
        System.out.println("2. UPDATE YEAR");
        System.out.println("3. UPDATE GENRE");
        System.out.println("4. UPDATE DURATION");
        System.out.print("> ");
    }

    private static void updateMovie(Movie movie, int option){
        Scanner fieldUpdate = new Scanner(System.in);

        switch (option) {
            case 1 -> {
                System.out.println("SET THE NEW TITLE FOR MOVIE " + movie.getId());
                String title = fieldUpdate.nextLine();
                movie.setTitle(title);
            }
            case 2 -> {
                System.out.println("SET THE NEW YEAR FOR MOVIE " + movie.getId());
                int year = fieldUpdate.nextInt();
                movie.setYear(year);
            }
            case 3 -> {
                System.out.println("SET THE NEW GENRE FOR MOVIE " + movie.getId());
                String genre = fieldUpdate.nextLine();
                movie.setGenre(genre);
            }
            case 4 -> {
                System.out.println("SET THE NEW DURATION FOR MOVIE " + movie.getId());
                int duration = fieldUpdate.nextInt();
                movie.setDuration(duration);
            }
        }
    }

    public static void insert(){
        Movie movie = createNewMovie();

        if (movieDAO.exists(movie.getTitle())){
            System.out.println("MOVIE ALREADY EXISTS");
        }else if (movieDAO.insert(movie)){
            System.out.println("SUCCESS!!!");
            System.out.println(movieDAO.search(movie.getTitle()));
        }else System.out.println("SOMETHING WENT WRONG.");

    }

    public static Movie createNewMovie(){

        Scanner insertInput = new Scanner(System.in);
        System.out.println("SET THE TITLE.");
        System.out.print("> ");
        String title = insertInput.next();
        System.out.println("SET THE YEAR.");
        System.out.print("> ");
        int year = insertInput.nextInt();
        System.out.println("SET THE NEW GENRE.");
        System.out.print("> ");
        String genre = insertInput.next();
        System.out.println("SET THE NEW DURATION.");
        System.out.print("> ");
        int duration = insertInput.nextInt();

        return new Movie(title,year,genre,duration);
    }

    public static void delete(){
        Scanner deleteInput = new Scanner(System.in);

        listMovies();
        System.out.println("SELECT MOVIE TO DELETE (ID)");
        System.out.print("> ");
        int movieDelete = deleteInput.nextInt();

        for (Movie movie: movieDAO.list()) {
            if (movie.getId() == movieDelete){
                if (movieDAO.delete(movie))
                    System.out.println("SUCCESS.");
            }
        }
    }

    public static void search(){
        Scanner searchInput = new Scanner(System.in);

        System.out.println("TYPE THE MOVIE YOU WANT TO SEARCH (TITLE)");
        System.out.print("> ");
        String movieSearch = searchInput.next();


        if (movieDAO.search(movieSearch) == null)
            System.out.println("NOT FOUND.");
        else System.out.println(movieDAO.search(movieSearch));
    }

    public static void searchByTitle(){
        Scanner searchTitleInput= new Scanner(System.in);

        System.out.println("TYPE THE MOVIE YOU WANT TO SEARCH (TITLE)");
        System.out.print("> ");
        String movieSearch = searchTitleInput.next();

        if (movieDAO.searchByTitle(movieSearch) == null)
            System.out.println("NOT FOUND.");
        else System.out.println(movieDAO.searchByTitle(movieSearch));
    }

    public static void searchByYear(){
        Scanner searchYearInput = new Scanner(System.in);

        System.out.println("TYPE THE MOVIE YOU WANT TO SEARCH (YEAR)");
        System.out.print("> ");
        int movieSearch = searchYearInput.nextInt();
        List<Movie> movieListByGenre = movieDAO.searchByYear(movieSearch);

        if (movieListByGenre.isEmpty())
            System.out.println("NO MOVIES FOUND.");
        else System.out.println(movieListByGenre);
    }

    public static void searchByGenre(){
        Scanner searchByGenre = new Scanner(System.in);

        System.out.println("TYPE THE MOVIE YOU WANT TO SEARCH (GENRE)");
        System.out.print("> ");
        String movieSearch = searchByGenre.next();
        List<Movie> movieListByGenre = movieDAO.searchByGenre(movieSearch);

        if (movieListByGenre.isEmpty())
            System.out.println("NO MOVIES FOUND.");
        else System.out.println(movieListByGenre);
    }

    public static void searchByDuration(){
        Scanner searchDurationInput = new Scanner(System.in);

        System.out.println("TYPE THE MOVIE YOU WANT TO SEARCH (YEAR)");
        System.out.print("> ");
        int movieSearch = searchDurationInput.nextInt();
        List<Movie> movieListByDuration = movieDAO.searchByDuration(movieSearch);

        if (movieListByDuration.isEmpty())
            System.out.println("NO MOVIES FOUND.");
        else System.out.println(movieListByDuration);
    }

}
