package xzn.service;

import xzn.domain.User;

public interface UserService {
    boolean regist(User user);

    User findUserByPassword(User user);

}
