package business.dao;

import java.util.List;

public interface IGenericDao <T,E>{
    List<T> findAll();
    T fingById(E id);
    void save(T t);
    void delete(E id);
}
