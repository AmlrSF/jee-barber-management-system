package Infrastructure;

import java.util.List;

public interface IDAOcrud<T> {
	
    // Create
    void create(T entity);

    // Read
    T findById(int id);
    List<T> findAll();

    // Update
    void update(T entity);

    // Delete
    void delete(T entity);
    
}
