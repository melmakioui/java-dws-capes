package data;

import business.DirectorDAO;
import business.DirectorDAOImpl;
import business.MovieDAO;
import business.MovieDAOImpl;
import data.pojos.MovieDirectorDTO;
import domain.Director;
import domain.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessMovieDirectorImpl implements DataAccessMovieDirector {

    private Connection connection;
    private MovieDAO movieDAO;
    private DirectorDAO directorDAO;
    private final String INSERT_MOVIE = "INSERT INTO movie (title,year,genre,duration) VALUES (?,?,?,?)";
    private final String INSERT_DIRECTOR = "INSERT INTO director (name,age) VALUES (?,?)";
    private final String INSERT_RELATIONSHIP = "INSERT INTO movie_director (idMovie,idDirector) VALUES (?,?)";
    private static final String SELECT_JOIN = "SELECT movie.*, director.* from movie,director where movie.id = director.id";

    public DataAccessMovieDirectorImpl() {
        this.movieDAO = new MovieDAOImpl();
        this.directorDAO = new DirectorDAOImpl();
        try {
            this.connection = ConnectionPostgres.getConnection();
        } catch (SQLException err) {
            System.out.println("ERROR TO CONNECT WITH DB");
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
    public boolean insertMovieDirector(Director director, Movie movie) {

        try (PreparedStatement pstmRelation = this.connection.prepareStatement(INSERT_RELATIONSHIP)) {

            this.connection.setAutoCommit(false);

            long idMovie = insertMovie(movie);
            long idDirector = insertDirector(director);

            System.out.println(idDirector);

            pstmRelation.setLong(1,idMovie);
            pstmRelation.setLong(2,idDirector);
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

    private long insertMovie(Movie movie) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_MOVIE, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setInt(2, movie.getYear());
            preparedStatement.setString(3, movie.getGenre());
            preparedStatement.setInt(4, movie.getDuration());

            int resultMovie = preparedStatement.executeUpdate();

            ResultSet resultSetMovie = preparedStatement.getGeneratedKeys();

            if (resultSetMovie.next())
                return resultSetMovie.getLong(1);
            else throw new SQLException();

        } catch (SQLException error) {
            System.out.println("ERROR");
        }
        return 0;
    }

    private long insertDirector(Director director) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_DIRECTOR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, director.getName());
            preparedStatement.setInt(2, director.getAge());

            List<Director> find = directorDAO.list();

            for (Director directorExists: find)
                if (directorExists.getName().equals(director.getName()))
                    return directorExists.getId();

            int resultDirector = preparedStatement.executeUpdate();

            ResultSet resultSetDirector = preparedStatement.getGeneratedKeys();

            if (resultSetDirector.next())
                return resultSetDirector.getLong(1);
            else throw new SQLException();

        } catch (SQLException error) {
            System.out.println("ERROR");
        }
        return 0;
    }

}
