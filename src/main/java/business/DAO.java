package business;

import java.util.List;

public interface DAO<T>{

    List<T> list();
    boolean init();
    boolean exists(String data);
    boolean update(T t);
    boolean insert(T t);
    boolean delete(T t);
    T search(String t);
}
