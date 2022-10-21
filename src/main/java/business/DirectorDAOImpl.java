package business;

import data.DataAccessDirector;
import data.DataAccessDirectorImpl;
import domain.Director;

import java.util.List;

public class DirectorDAOImpl implements DirectorDAO{

    private DataAccessDirector dataAccessDirector;

    public DirectorDAOImpl() {
        this.dataAccessDirector = new DataAccessDirectorImpl();
    }

    @Override
    public List<Director> list() {
       List<Director> directorList =  this.dataAccessDirector.list();
       return directorList;
    }

    @Override
    public boolean init() {
        return false; //delete from *
    }

    @Override
    public boolean exists(String data) {
        return dataAccessDirector.exists(data);
    }

    @Override
    public boolean update(Director director) {
        return dataAccessDirector.update(director);
    }

    @Override
    public boolean insert(Director director) {
        return dataAccessDirector.insert(director);
    }

    @Override
    public boolean delete(Director director) {
        return dataAccessDirector.delete(director);
    }

    @Override
    public Director search(String director) {
        Director foundedDirector = dataAccessDirector.search(director);
        if (foundedDirector == null)
            return new Director();
        return foundedDirector;
    }

    @Override
    public Director searchByName(String name) {
        Director director =  dataAccessDirector.searchByName(name);
        if (director == null)
            return new Director();
        return director;
    }

    @Override
    public List<Director> searchByAge(int age) {
       List<Director> directorList = dataAccessDirector.searchByAge(age);
       return directorList;
    }
}
