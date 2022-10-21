package business;

import domain.Director;

import java.util.List;

public interface DirectorDAO extends DAO<Director>{

    String searchByName(String name);
    List<Director> searchByAge(int age);

}
