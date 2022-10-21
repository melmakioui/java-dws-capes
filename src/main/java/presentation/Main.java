package presentation;

import business.DirectorDAOImpl;
import business.MovieDAOImpl;
import domain.Director;

public class Main {
    public static void main(String[] args) {

        MovieDAOImpl movieDAO = new MovieDAOImpl();

        System.out.println(movieDAO.searchByDuration(80));
    }
}