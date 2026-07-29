package ng.pharmacy.data.repositories;

import ng.pharmacy.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepository{

    private int nextId = 0;
    private static final List<User> users = new ArrayList<>();

        @Override
    public User save(User user) {
        if (!isNew(user)) return user;
        user.setId(++nextId);
        users.add(user);
        return user;
    }

    private boolean isNew(User user) {
        for (User person : users){
            if (person.getUsername().equals(user.getUsername())) return false;
        }
        return true;
    }

    @Override
    public User findByUsername(String username) {
        for (User user : users){
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    @Override
    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(int id) {
        User found = findById(id);
        if  (found == null) return;
        users.remove(found);
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public void clear() {
        users.clear();
        nextId = 0;
    }
}
