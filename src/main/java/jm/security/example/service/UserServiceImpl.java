package jm.security.example.service;

import jm.security.example.dao.UserDao;
import jm.security.example.model.Role;
import jm.security.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public List<User> index() {
        return userDao.index();
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public User show(long id) {
        return userDao.show(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public Role getRoleById(long id) {
        return userDao.getRoleById(id);
    }




}
