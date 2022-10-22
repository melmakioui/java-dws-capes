package presentation;

import data.config.ConnectionDatabase;
import data.config.ConnectionMysql;
import data.config.ConnectionPostgres;

import java.util.Scanner;

public class Presentation {

    private static ConnectionDatabase connectionDatabase;
    private static Scanner input = new Scanner(System.in);

    public static void init(){
        showDataBaseOptions();
        int dataBase = input.nextInt();
        initDatabase(dataBase);

        do {
            showOptions();
            int option = input.nextInt();
            action(option);
        }while (true);
    }

    private static void initDatabase(int choice){

        switch (choice) {
            case 1 -> connectionDatabase = new ConnectionMysql();
            case 2 -> connectionDatabase = new ConnectionPostgres();
        }
    }

    private static void action(int option){

        if (option > 20 && option < 0){
            System.out.println("INVALID OPTION");
            init();
        }

        if (option < 3 && option > 0)
            initMovieDirector(option);

        if (option < 13 && option > 2)
            initMovie(option);

        if (option < 21 && option > 12)
            initDirector(option);

        if (option == 21)
            exit();
    }

    private static void initMovieDirector(int option){
        MovieDirectorPresentation.init(connectionDatabase);

        switch (option) {
            case 1 -> MovieDirectorPresentation.listMoviesDirectors();
            case 2 -> MovieDirectorPresentation.associateMovieToDirector();
        }
    }

    private static void initMovie(int option){

        MoviePresentation.init(connectionDatabase);

        switch (option) {
            case 3 -> MoviePresentation.listMovies();
            case 4 -> MoviePresentation.exists();
            case 5 -> MoviePresentation.update();
            case 6 -> MoviePresentation.insert();
            case 7 -> MoviePresentation.delete();
            case 8 -> MoviePresentation.search();
            case 9 -> MoviePresentation.searchByTitle();
            case 10 -> MoviePresentation.searchByYear();
            case 11 -> MoviePresentation.searchByGenre();
            case 12 -> MoviePresentation.searchByDuration();
        }
    }

    private static void initDirector(int option){
        DirectorPresentation.init(connectionDatabase);

        switch (option) {
            case 13 -> DirectorPresentation.listDirector();
            case 14 -> DirectorPresentation.exists();
            case 15 -> DirectorPresentation.update();
            case 16 -> DirectorPresentation.insert();
            case 17 -> DirectorPresentation.delete();
            case 18 -> DirectorPresentation.search();
            case 19 -> DirectorPresentation.searchByName();
            case 20 -> DirectorPresentation.searchByAge();
        }
    }

    private static void showDataBaseOptions(){
        System.out.println();
        System.out.println("***MOVIES CATALOGUE***");
        System.out.println("WHICH DATABASE YOU WANT TO USE");
        System.out.println("1. MySQL");
        System.out.println("2. PostgresSQL");
        System.out.print("> ");
    }

    private static void showOptions(){
        System.out.println();
        System.out.println("**SELECT ONE OPTION**");
        System.out.println("-----MOVIE & DIRECTOR-----");
        System.out.println("1. SELECT MOVIES AND THEIR DIRECTORS");
        System.out.println("2. ASSOCIATE MOVIE WITH DIRECTOR");
        showMovieOptions();
        showDirectorOptions();
        System.out.println("21. EXIT");
        System.out.print("> ");
    }

    private static void showMovieOptions(){
        System.out.println();
        System.out.println("-----MOVIE-----");
        System.out.println("3. LIST MOVIES");
        System.out.println("4. CHECK IF MOVIE EXISTS");
        System.out.println("5. UPDATE MOVIE");
        System.out.println("6. INSERT MOVIE");
        System.out.println("7. DELETE MOVIE");
        System.out.println("8. SEARCH MOVIE");
        System.out.println("9. SEARCH MOVIE BY TITLE");
        System.out.println("10. SEARCH MOVIE BY YEAR");
        System.out.println("11. SEARCH MOVIE BY GENRE");
        System.out.println("12. SEARCH MOVIE BY DURATION");

    }

    private static void showDirectorOptions(){
        System.out.println();
        System.out.println("-----DIRECTOR-----");
        System.out.println("13. LIST DIRECTORS");
        System.out.println("14. CHECK IF DIRECTOR EXISTS");
        System.out.println("15. UPDATE DIRECTOR");
        System.out.println("16. INSERT DIRECTOR");
        System.out.println("17. DELETE DIRECTOR");
        System.out.println("18. SEARCH DIRECTOR");
        System.out.println("19. SEARCH DIRECTOR BY NAME");
        System.out.println("20. SEARCH DIRECTOR BY AGE");
    }

    private static void csvOptions(){
        System.out.println("EXPORT");
        System.out.println("1. EXPORT DATA");

    }

    private static void exit(){
        System.exit(1);
    }
}
