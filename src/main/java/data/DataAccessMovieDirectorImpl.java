package data;

import business.DirectorDAO;
import business.DirectorDAOImpl;
import business.MovieDAO;
import business.MovieDAOImpl;
import data.dtos.MovieDirectorDTO;
import domain.Director;
import domain.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessMovieDirectorImpl implements DataAccessMovieDirector{

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
       try{
           this.connection = ConnectionMysql.getConnection();
       }catch (SQLException err){
           System.out.println("ERROR TO CONNECT WITH DB");
       }
    }

    @Override
    public List<MovieDirectorDTO> getMovieDirectorList() {

        List<MovieDirectorDTO> moviesWithDirector = new ArrayList<>();

        try(Statement stm = this.connection.createStatement();
            ResultSet result = stm.executeQuery(SELECT_JOIN)){

            while (result.next()){
                int id = result.getInt("id");
                String title = result.getString("title");
                int year = result.getInt("year");
                String genre = result.getString("genre");
                int duration = result.getInt("duration");
                String name = result.getString("name");
                int age = result.getInt("age");

                moviesWithDirector.add(new MovieDirectorDTO(id,title,year,genre,duration,name,age));
            }
        }catch (SQLException error){
            System.out.println("ERROR LISTING MOVIES " + error);
        }
        return moviesWithDirector;
    }

    @Override
    public boolean insertMovieDirector(Director director, Movie movie) {

        try(PreparedStatement pstmMovie = this.connection.prepareStatement(INSERT_MOVIE,Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pstmDirector = this.connection.prepareStatement(INSERT_DIRECTOR,Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pstmRelation = this.connection.prepareStatement(INSERT_RELATIONSHIP)) {

            this.connection.setAutoCommit(false);

            pstmMovie.setString(1,movie.getTitle());
            pstmMovie.setInt(2,movie.getYear());
            pstmMovie.setString(3,movie.getGenre());
            pstmMovie.setInt(4,movie.getDuration());

            int resultMovie = pstmMovie.executeUpdate();

            pstmDirector.setString(1,director.getName());
            pstmDirector.setInt(2,director.getAge());

            int resultDirector = pstmDirector.executeUpdate();

            ResultSet resultSetMovie = pstmMovie.getGeneratedKeys();
            ResultSet resultSetDirector = pstmDirector.getGeneratedKeys();

            if (resultSetMovie.next()){
                long idMovie = resultSetMovie.getLong(1);
                System.out.println(idMovie);
                pstmRelation.setLong(1,idMovie);
            }

            if (resultSetDirector.next()){
                long idDirector  = resultSetDirector.getLong(1);
                System.out.println(idDirector);

                pstmRelation.setLong(2,idDirector);
            }

            pstmRelation.executeUpdate();


            this.connection.commit();
      }catch (SQLException error){
            System.out.println("ERROR INSERTING MOVIE " + error);

            try {
                this.connection.rollback();
            }catch (SQLException err){
                System.out.println("ERROR ROLLBACK");
            }
      }

     return false;
    }

    private boolean insertMovie(Movie movie){
        return false;
    }

    private boolean insertDirector(Director movie){
        return false;
    }

    private boolean insertMovieDirector(Movie movie){
        return false;
    }
}
