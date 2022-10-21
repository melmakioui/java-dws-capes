package data;

import domain.Director;

import java.util.List;

public interface DataAccessDirector extends DataAccess<Director> {

    Director searchByName(String name);
    List<Director> searchByAge(int age);
}
