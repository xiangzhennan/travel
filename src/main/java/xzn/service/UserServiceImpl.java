package xzn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzn.dao.userDao;
import xzn.domain.User;

import javax.management.relation.RelationNotFoundException;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private userDao dao;
    @Override
    public boolean regist(User user) {
        try{int i = dao.registUser(user);
        if (i==1)return true;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public User findUserByPassword(User user) {
        return dao.findUserByPassword(user);
    }
}
