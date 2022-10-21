package data;

import domain.Director;
import domain.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessDirector implements DataAccess<Director> {

    private Connection connection;
    private static final String SELECT = "SELECT * FROM director";
    private static final String SELECT_LIKE = "SELECT * FROM director WHERE name LIKE ?";
    private static final String UPDATE = "UPDATE director SET name = ?, age = ? WHERE id = ?";
    private static final String INSERT = "INSERT INTO director (name,age) VALUES  (?,?)";
    private static final String DELETE = "DELETE FROM director WHERE id=?";


    public DataAccessDirector() { //Reutilizar constructor
        try {
            this.connection = ConnectionMysql.getConnection();
        }catch (SQLException error){
            System.out.println("ERROR TO CONNECT WITH DATABASE " + error );
        }
    }

    @Override
    public List<Director> list() {

        List<Director> directors = new ArrayList<>();

        try(Statement stm = this.connection.createStatement();
            ResultSet result = stm.executeQuery(SELECT)){

            while (result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                int age = result.getInt("age");

                directors.add(new Director(id,name,age));
            }
        }catch (SQLException error) {
            System.out.println("ERROR LISTING DIRECTORS " + error);
        }
        return directors;
    }

    @Override
    public boolean exists(String director) {

        String directorToSearch = "%" + director + "%";

        try (PreparedStatement pstm = this.connection.prepareStatement(SELECT_LIKE)) {
            pstm.setString(1, directorToSearch);

            ResultSet rs = pstm.executeQuery();

            if (rs.next())
                return true;

        } catch (SQLException error) {
            System.out.println("ERROR TO SEARCH " + error);
        }
        return false;
    }

    @Override
    public boolean update(Director director) {

        Director updateDirector = director;
        int directorId = updateDirector.getId();

        try(PreparedStatement pstm = this.connection.prepareStatement(UPDATE) ) {
            pstm.setString(2, director.getName());
            pstm.setInt(3, director.getAge());
            pstm.setInt(1, directorId);

            int result = pstm.executeUpdate();

            if (result > 0)
                return true;

        }catch (SQLException error){
            System.out.println("ERROR UPDATING DIRECTOR");
        }
        return false;
    }

    @Override
    public boolean insert(Director director) {

        try (PreparedStatement pstm = this.connection.prepareStatement(INSERT)) {
            pstm.setString(1, director.getName());
            pstm.setInt(2, director.getAge());

            int newMovie = pstm.executeUpdate();

            if (newMovie > 0)
                return true;

        } catch (SQLException error) {
            System.out.println("ERROR TO INSERT DATA " + error);
        }
        return false;
    }

    @Override
    public boolean delete(Director director) {

        int directorDelete = director.getId();

        try(PreparedStatement pstm = this.connection.prepareStatement(DELETE)){
            pstm.setInt(1,directorDelete);

            int result = pstm.executeUpdate();

            if (result > 0)
                return true;

        }catch (SQLException error){
            System.out.println("ERROR DELETING DIRECTOR " + error);
        }

        return false;
    }

    @Override
    public Director search(String director) {

        String directorToSearch = "%" + director + "%";

        try (PreparedStatement pstm = this.connection.prepareStatement(SELECT_LIKE)) {

            pstm.setString(1, directorToSearch);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                return new Director(id,name,age);
            }
        } catch (SQLException err) {
            System.out.println("ERROR TO SEARCH " + err);
        }
        return null;
    }
}
