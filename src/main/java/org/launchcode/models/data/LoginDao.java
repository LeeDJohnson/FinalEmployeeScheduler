package org.launchcode.models.data;

import org.launchcode.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface LoginDao extends CrudRepository<User, Integer> {

    boolean isLoggedIn();

    void setCurrentUser(User user);

    boolean authenticateUser(User user);

    boolean isCurrentUserAdmin();

}
