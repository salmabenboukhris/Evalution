package ma.project.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<T,ID extends Serializable> {   // T est unr classe generique

    boolean create (T o);
    boolean update (T o);
    boolean delete(T o);
    T findById (ID id );

    T findById(int id);

    List <T> findAll ();

}
