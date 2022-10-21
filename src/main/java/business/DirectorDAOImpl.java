package business;

import domain.Director;

import java.util.List;

public class DirectorDAOImpl implements DirectorDAO{
    @Override
    public List<Director> list() {
        return null;
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public boolean exists(String data) {
        return false;
    }

    @Override
    public boolean update(Director director) {
        return false;
    }

    @Override
    public boolean insert(Director director) {
        return false;
    }

    @Override
    public boolean delete(Director director) {
        return false;
    }

    @Override
    public Director search(String t) {
        return null;
    }

    @Override
    public String searchByName(String name) {
        return null;
    }

    @Override
    public List<Director> searchByAge(int age) {
       return null;
    }
}
