package com.fanchaojian.service.impl;

import com.fanchaojian.dao.IUserDao;
import com.fanchaojian.domain.User;
import com.fanchaojian.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-25 - 14:46
 */
@Service
public class IUserserviceImpl implements IUserService {
    @Autowired
    private IUserDao userDao ;

    @Override
    public User addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByOpendId(String opendID) {
        return userDao.findByOpendId(opendID);
    }

    @Override
    public User findByUnionId(String unionID) {
        return userDao.findByUnionId(unionID);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void deleteUser(int uid) {
        userDao.deleteUser(uid);
    }
}
