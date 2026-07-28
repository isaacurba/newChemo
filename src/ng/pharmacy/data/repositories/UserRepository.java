package ng.pharmacy.data.repositories;
import ng.pharmacy.data.models.User;

import java.util.List;

public interface UserRepository {

    User save(User user);
    User findByUsername(String username);
    User findById(int id);
    List<User> findAll();
    void deleteById(int id);
    int count();
    void clear();
}
