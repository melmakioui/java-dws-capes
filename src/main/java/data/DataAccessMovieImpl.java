package data;

import data.config.ConnectionDatabase;
import domain.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessMovieImpl implements DataAccessMovie {

    private Connection connection;
    private ConnectionDatabase connectionDatabase;
    private final String SELECT = "SELECT * FROM movie";
    private final String SELECT_LIKE = "SELECT * FROM movie WHERE title LIKE ?";
    private final String UPDATE = "UPDATE movie SET title = ?, year = ?,  genre = ?, duration = ? WHERE id = ?";
    private final String INSERT = "INSERT INTO movie (title,year,genre,duration) VALUES  (?,?,?,?)";
    private final String DELETE = "DELETE FROM movie WHERE id=?";
    private final String SELECT_BY_TITLE = "SELECT * FROM movie WHERE title LIKE ?";
    private final String SELECT_BY_YEAR = "SELECT * FROM movie WHERE year = ?";
    private final String SELECT_BY_GENRE = "SELECT * FROM movie WHERE genre LIKE ?";
    private final String SELECT_BY_DURATION = "SELECT * FROM movie WHERE duration = ?";

    public DataAccessMovieImpl(ConnectionDatabase connectionDatabase) {
        this.connectionDatabase = connectionDatabase;
        try {
            this.connection = connectionDatabase.getConnection();
        } catch (SQLException error) {
            System.out.println("ERROR TO CONNECT WITH DATABASE " + error);
        }
    }

    @Override
    public List<Movie> list() {
        List<Movie> movies = new ArrayList<>();

        try (Statement stm = this.connection.createStatement();
             ResultSet result = stm.executeQuery(SELECT)) {

            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                int year = result.getInt("year");
                String genre = result.getString("genre");
                int duration = result.getInt("duration");

                movies.add(new Movie(id, title, year, genre, duration));
            }
        } catch (SQLException error) {
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
            pstm.setInt(5, updatedMovieId);

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
        try (PreparedStatement pstm = this.connection.prepareStatement(INSERT)) {

            pstm.setString(1, movie.getTitle());
            pstm.setInt(2, movie.getYear());
            pstm.setString(3, movie.getGenre());
            pstm.setInt(4, movie.getDuration());

            int newMovie = pstm.executeUpdate();

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

                return new Movie(id, title, year, genre, duration);
            }

        } catch (SQLException err) {
            System.out.println("ERROR TO SEARCH " + err);
        }
        return null;
    }

    @Override
    public Movie searchByTitle(String movieTitle) {
        String movieByTitle = "%" + movieTitle + "%";

        try (PreparedStatement pstm = this.connection.prepareStatement(SELECT_BY_TITLE)) {

            pstm.setString(1, movieByTitle);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");

                return new Movie(id, title, year, genre, duration);
            }

        } catch (SQLException err) {
            System.out.println("ERROR TO SEARCH " + err);
        }
        return null;
    }

    @Override
    public List<Movie> searchByYear(int movieYear) {

        int movieByYear = movieYear;
        List<Movie> movieList = new ArrayList<>();

        try (PreparedStatement pstm = this.connection.prepareStatement(SELECT_BY_YEAR)) {

            pstm.setInt(1, movieByYear);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");

                movieList.add(new Movie(id, title, year, genre, duration));
            }

            return movieList;
        } catch (SQLException err) {
            System.out.println("ERROR TO SEARCH " + err);
        }
        return null;
    }

    @Override
    public List<Movie> searchByGenre(String movieGenre) {
        String movieByGenre = "%" + movieGenre + "%";
        List<Movie> movieList = new ArrayList<>();

        try (PreparedStatement pstm = this.connection.prepareStatement(SELECT_BY_GENRE)) {

            pstm.setString(1, movieByGenre);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");

                movieList.add(new Movie(id, title, year, genre, duration));
            }
            return movieList;
        } catch (SQLException err) {
            System.out.println("ERROR TO SEARCH " + err);
        }
        return null;
    }

    @Override
    public List<Movie> searchByDuration(int movieDuration) {
        int movieByDuration = movieDuration;
        List<Movie> movieList = new ArrayList<>();

        try (PreparedStatement pstm = this.connection.prepareStatement(SELECT_BY_DURATION)) {

            pstm.setInt(1, movieByDuration);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");

                movieList.add(new Movie(id, title, year, genre, duration));
            }
            return movieList;
        } catch (SQLException err) {
            System.out.println("ERROR TO SEARCH " + err);
        }
        return null;
    }
}
