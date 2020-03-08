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
}
