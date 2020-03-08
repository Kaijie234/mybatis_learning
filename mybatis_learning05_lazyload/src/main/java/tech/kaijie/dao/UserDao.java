package tech.kaijie.dao;

import tech.kaijie.domain.User;

import java.util.List;

/**
 * User的持久层接口
 */
public interface UserDao {

    /**
     * 查询所有用户信息，包括其所有的账户信息
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户id，查询用户信息
     * @param id
     * @return
     */
    User findById(Integer id);
}
