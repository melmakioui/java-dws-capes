package data;

import business.DirectorDAO;
import business.DirectorDAOImpl;
import business.MovieDAO;
import business.MovieDAOImpl;
import data.config.ConnectionDatabase;
import data.pojos.MovieDirectorDTO;
import domain.Director;
import domain.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessMovieDirectorImpl implements DataAccessMovieDirector {

    private Connection connection;
    private ConnectionDatabase connectionDatabase;
    private MovieDAO movieDAO;
    private DirectorDAO directorDAO;
    private final String INSERT_MOVIE = "INSERT INTO movie (title,year,genre,duration) VALUES (?,?,?,?)";
    private final String INSERT_DIRECTOR = "INSERT INTO director (name,age) VALUES (?,?)";
    private final String INSERT_RELATIONSHIP = "INSERT INTO movie_director (idMovie,idDirector) VALUES (?,?)";
    private static final String SELECT_JOIN = "SELECT movie.*, director.* from movie,director where movie.id = director.id";

    public DataAccessMovieDirectorImpl(ConnectionDatabase connectionDatabase) {
        this.connectionDatabase = connectionDatabase;
        this.movieDAO = new MovieDAOImpl(this.connectionDatabase);
        this.directorDAO = new DirectorDAOImpl(this.connectionDatabase);

        try {
            this.connection = connectionDatabase.getConnection();
        } catch (SQLException error) {
            System.out.println("ERROR TO CONNECT WITH DATABASE " + error);
        }
    }

    @Override
    public List<MovieDirectorDTO> getMovieDirectorList() {

        List<MovieDirectorDTO> moviesWithDirector = new ArrayList<>();

        try (Statement stm = this.connection.createStatement();
             ResultSet result = stm.executeQuery(SELECT_JOIN)) {

            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                int year = result.getInt("year");
                String genre = result.getString("genre");
                int duration = result.getInt("duration");
                String name = result.getString("name");
                int age = result.getInt("age");

                moviesWithDirector.add(new MovieDirectorDTO(id, title, year, genre, duration, name, age));
            }
        } catch (SQLException error) {
            System.out.println("ERROR LISTING MOVIES " + error);
        }
        return moviesWithDirector;
    }

    @Override
    public boolean insertMovieDirector(int movie, int director) {

        try (PreparedStatement pstmRelation = this.connection.prepareStatement(INSERT_RELATIONSHIP)) {

            this.connection.setAutoCommit(false);

            pstmRelation.setLong(1, movie);
            pstmRelation.setLong(2, director);
            pstmRelation.executeUpdate();

            this.connection.commit();
            return true;
        } catch (SQLException error) {
            System.out.println("ERROR INSERTING MOVIE " + error);
            try {
                this.connection.rollback();
            } catch (SQLException err) {
                System.out.println("ERROR ROLLBACK");
            }
        }
        return false;
    }
}
