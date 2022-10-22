package presentation;

import business.DirectorDAO;
import business.DirectorDAOImpl;
import data.config.ConnectionDatabase;
import domain.Director;

import java.util.List;
import java.util.Scanner;

public class DirectorPresentation {

    private static Scanner directorInput = new Scanner(System.in);
    private static ConnectionDatabase connectionDatabase;
    private static DirectorDAO directorDAO;

    public static void init(ConnectionDatabase conn) {
        connectionDatabase = conn;
        directorDAO = new DirectorDAOImpl(connectionDatabase);
    }

    public static void listDirector() {
        List<Director> directorList = directorDAO.list();

        if (directorList.isEmpty())
            System.out.println("NO DIRECTORS.");
        else System.out.println(directorList);
    }

    public static void exists() {
        System.out.println("TYPE THE DIRECTOR NAME");
        System.out.print("> ");
        String directorExists = directorInput.nextLine();

        if (directorDAO.exists(directorExists))
            System.out.println(directorDAO.search(directorExists));
        else System.out.println("DIRECTOR DOES NOT EXIST");
    }

    public static void update() {
        listDirector();
        System.out.println("SELECT DIRECTOR TO UPDATE (ID)");
        System.out.print(">");
        int directorUpdate = directorInput.nextInt();

        Director updateDirector = null;

        for (Director director : directorDAO.list())
            if (director.getId() == directorUpdate)
                updateDirector = director;

        showUpdateDirectorOptions();
        int fieldToUpdate = directorInput.nextInt();
        updateDirector(updateDirector, fieldToUpdate);

        if (directorDAO.update(updateDirector))
            System.out.println("UPDATED");
    }

    private static void showUpdateDirectorOptions() {
        System.out.println("*UPDATING DIRECTOR*");
        System.out.println("1. UPDATE NAME");
        System.out.println("2. UPDATE AGE");
    }

    private static void updateDirector(Director director, int option) {
        Scanner fieldUpdate = new Scanner(System.in);

        switch (option) {
            case 1 -> {
                System.out.println("SET THE NEW NAME FOR DIRECTOR " + director.getId());
                String title = fieldUpdate.nextLine();
                director.setName(title);
            }
            case 2 -> {
                System.out.println("SET THE NEW AGE FOR DIRECTOR " + director.getId());
                int year = fieldUpdate.nextInt();
                director.setAge(year);
            }
        }
    }

    public static void insert() {
        Director director = createNewDirector();

        if (directorDAO.exists(director.getName())) {
            System.out.println("DIRECTOR ALREADY EXISTS");
        } else if (directorDAO.insert(director)) {
            System.out.println("SUCCESS!!!");
            System.out.println(directorDAO.search(director.getName()));
        } else System.out.println("SOMETHING WENT WRONG.");

    }

    public static Director createNewDirector() {
        Scanner insertInput = new Scanner(System.in);

        System.out.println("SET THE TITLE.");
        System.out.print("> ");
        String name = insertInput.next();
        System.out.println("SET THE YEAR.");
        System.out.print("> ");
        int age = insertInput.nextInt();

        return new Director(name, age);
    }

    public static void delete() {
        Scanner deleteInput = new Scanner(System.in);

        listDirector();
        System.out.println("SELECT DIRECTOR TO DELETE (ID)");
        System.out.print("> ");
        int directorDelete = deleteInput.nextInt();

        for (Director director : directorDAO.list()) {
            if (director.getId() == directorDelete) {
                if (directorDAO.delete(director))
                    System.out.println("SUCCESS.");
            }
        }
    }

    public static void search() {
        Scanner searchInput = new Scanner(System.in);

        System.out.println("TYPE THE DIRECTOR YOU WANT TO SEARCH (NAME)");
        System.out.print("> ");
        String directorSearch = searchInput.next();
        Director director = directorDAO.search(directorSearch);

        if (director == null)
            System.out.println("NOT FOUND.");
        else System.out.println(director);
    }

    public static void searchByName() {
        Scanner searchNameInput = new Scanner(System.in);

        System.out.println("TYPE THE DIRECTOR YOU WANT TO SEARCH (NAME)");
        System.out.print("> ");
        String directorName = searchNameInput.next();

        Director director = directorDAO.searchByName(directorName);

        if (director == null)
            System.out.println("NOT FOUND");
        else System.out.println(director);
    }

    public static void searchByAge() {
        Scanner searchAgeInput = new Scanner(System.in);

        System.out.println("TYPE THE DIRECTOR YOU WANT TO SEARCH (AGE)");
        System.out.print("> ");
        int directorAge = searchAgeInput.nextInt();

        List<Director> directorList = directorDAO.searchByAge(directorAge);

        if (directorList.isEmpty())
            System.out.println("NO DIRECTORS FOUND WITH AGE " + directorAge);
        else System.out.println(directorList);
    }
}