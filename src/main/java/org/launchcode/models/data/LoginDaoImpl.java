package org.launchcode.models.data;

import org.launchcode.models.User;
import java.util.ArrayList;
import java.util.List;

public class LoginDaoImpl implements LoginDao {

    private final List<User> validUsers = new ArrayList<>();


    public LoginDaoImpl() {
        validUsers.add(new User("ljohnson", "password", true));
        validUsers.add(new User("employee", "123", false));
        validUsers.add(new User("nick", "123", true));
    }


    private User currentUser = null;

    public boolean authenticateUser(User user) {

        String username = user.getUserName();
        String password = user.getPassword();

        System.out.println("user " + username);
        System.out.println("password " + password);

        for (User aUser : validUsers) {
            if (aUser.getUserName().equals(username)) {
                if (aUser.getPassword().equals(password)) {
                    setCurrentUser(aUser);
                    return true;
                } else {
                    System.out.println("bad password");
                    return false;
                }
            }
        }

        System.out.println("bad username");
        return false;

    }

    public boolean isCurrentUserAdmin() {
        return isLoggedIn() && currentUser.getAdmin();
    }


    public boolean isLoggedIn() {
        return currentUser != null;
    }

    @Override
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    
    @Override
    public <S extends User> Iterable<S> save(Iterable<S> entities) {
        return null;
    }

    @Override
    public User findOne(Integer integer) {
        return null;
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAll(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void delete(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
