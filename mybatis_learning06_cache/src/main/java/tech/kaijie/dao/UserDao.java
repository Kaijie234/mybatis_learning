package tech.kaijie.dao;

import tech.kaijie.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户id查询用户信息
     */
    User findByUserId(Integer userId);
}
