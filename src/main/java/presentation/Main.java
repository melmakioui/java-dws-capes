package presentation;

import business.MovieDAOImp;
import domain.Movie;

public class Main {
    public static void main(String[] args) {

        MovieDAOImp movieDAOImp = new MovieDAOImp();

        System.out.println(movieDAOImp.list());
    }
}