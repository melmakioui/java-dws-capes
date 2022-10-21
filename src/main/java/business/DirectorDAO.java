package business;

import domain.Director;

import java.util.List;

public interface DirectorDAO extends DAO<Director>{

    Director searchByName(String name);
    List<Director> searchByAge(int age);

}
