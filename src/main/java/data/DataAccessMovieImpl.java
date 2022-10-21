package data;

import domain.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessMovieImpl implements DataAccessMovie {

    private Connection connection;
    private final String SELECT = "SELECT * FROM movie";
    private final String SELECT_LIKE = "SELECT * FROM movie WHERE title LIKE ?";
    private final String UPDATE = "UPDATE movie SET title = ?, year = ?,  genre = ?, duration = ? WHERE id = ?";
    private final String INSERT = "INSERT INTO movie (title,year,genre,duration) VALUES  (?,?,?,?)";
    private final String DELETE = "DELETE FROM movie WHERE id=?";
    private final String SELECT_JOIN = "SELECT movie.*, director.name from movie,director where movie.id = director.id";

    public DataAccessMovieImpl(){
        try {
            this.connection = ConnectionMysql.getConnection();
        }catch (SQLException error){
            System.out.println("ERROR TO CONNECT WITH DATABASE " + error );
        }
    }

    @Override
    public List<Movie> list() {
        List<Movie> movies = new ArrayList<>();

        try(Statement stm = this.connection.createStatement();
            ResultSet result = stm.executeQuery(SELECT)){

            while (result.next()){
                int id = result.getInt("id");
                String title = result.getString("title");
                int year = result.getInt("year");
                String genre = result.getString("genre");
                int duration = result.getInt("duration");

                movies.add(new Movie(id,title,year,genre,duration));
            }
        }catch (SQLException error){
            System.out.println("ERROR LISTING MOVIES " + error);
        }
        return movies;
    }

    @Override
    public boolean exists(String data) {
        String movieToSearch = "%" + data + "%";

        try (PreparedStatement pstm = this.connection.prepareStatement(SELECT_LIKE)) {
            pstm.setString(1, movieToSearch);

            ResultSet rs = pstm.executeQuery();

            if (rs.next())
                return true;

        } catch (SQLException error) {
            System.out.println("ERROR TO SEARCH " + error);
        }
        return false;
    }

    @Override
    public boolean update(Movie movie) {
        Movie updatedMovie = movie;
        int updatedMovieId = updatedMovie.getId();

        try (PreparedStatement pstm = this.connection.prepareStatement(UPDATE)) {
            pstm.setString(1, movie.getTitle());
            pstm.setInt(2, movie.getYear());
            pstm.setString(3, movie.getGenre());
            pstm.setInt(4, movie.getDuration());
            pstm.setInt(5,updatedMovieId);

            int result = pstm.executeUpdate();

            if (result > 0)
                return true;

        } catch (SQLException error) {
            System.out.println("ERROR TO UPDATE Movie " + error);
        }
        return false;
    }

    @Override
    public boolean insert(Movie movie) {
        try (PreparedStatement pstm = this.connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, movie.getTitle());
            pstm.setInt(2, movie.getYear());
            pstm.setString(3, movie.getGenre());
            pstm.setInt(4, movie.getDuration());

            int newMovie = pstm.executeUpdate();

            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next())
                System.out.println(generatedKeys.getLong(1));

            if (newMovie > 0)
                return true;

        } catch (SQLException error) {
            System.out.println("ERROR TO INSERT DATA " + error);
        }
        return false;
    }

    @Override
    public boolean delete(Movie movie) {
        int movieDelete = movie.getId();

        try (PreparedStatement pstm = this.connection.prepareStatement(DELETE)) {
            pstm.setInt(1, movieDelete);
            int result = pstm.executeUpdate();

            if (result > 0)
                return true;

        } catch (SQLException error) {
            System.out.println("ERROR TO DELETE " + error);
        }
        return false;
    }

    @Override
    public Movie search(String data) {
        String movieToSearch = "%" + data + "%";

        try (PreparedStatement pstm = this.connection.prepareStatement(SELECT_LIKE)) {

            pstm.setString(1, movieToSearch);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");

                return new Movie(id,title,year,genre,duration);
            }

        } catch (SQLException err) {
            System.out.println("ERROR TO SEARCH " + err);
        }
        return null;
    }

    @Override
    public Movie searchByTitle(String title) {
        return null;
    }

    @Override
    public List<Movie> searchByYear(int year) {
        return null;
    }

    @Override
    public List<Movie> searchByGenre(String genre) {
        return null;
    }

    @Override
    public List<Movie> searchByDuration(int duration) {
        return null;
    }
}
