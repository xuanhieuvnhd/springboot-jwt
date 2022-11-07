package hieu.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import hieu.entities.User;

@Service
public class UserService {
    public static List<User> listUser = new ArrayList<User>();
    static {
        User admin = new User(1, "adm", "adm");
        admin.setRoles(new String[] { "ROLE_ADMIN" });
        User user = new User(2, "htc", "htc");
        user.setRoles(new String[] { "ROLE_USER" });
        listUser.add(admin);
        listUser.add(user);
    }
    public List<User> findAll() {
        return listUser;
    }
    public User findById(int id) {
        for (User user : listUser) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    public boolean add(User user) {
        for (User userExist : listUser) {
            if (user.getId() == userExist.getId() || StringUtils.equals(user.getUsername(), userExist.getUsername())) {
                return false;
            }
        }
        listUser.add(user);
        return true;
    }
    public void delete(int id) {
        listUser.removeIf(user -> user.getId() == id);
    }
    public User loadUserByUsername(String username) {
        for (User user : listUser) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public boolean checkLogin(User user) {
        for (User userExist : listUser) {
            if (StringUtils.equals(user.getUsername(), userExist.getUsername())
                    && StringUtils.equals(user.getPassword(), userExist.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
