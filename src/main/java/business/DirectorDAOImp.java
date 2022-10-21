package business;

import domain.Director;

import java.util.List;

public class DirectorDAOImp implements DAO<Director>{

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
}
