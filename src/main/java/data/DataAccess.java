package data;

import java.util.List;

public interface DataAccess<T> {

    List<T> list();
    boolean exists(String data);
    boolean update(T t);
    boolean insert(T t);
    boolean delete(T t);
    T search(String data);
}
