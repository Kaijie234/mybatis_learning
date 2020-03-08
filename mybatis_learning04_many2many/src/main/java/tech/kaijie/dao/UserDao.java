package tech.kaijie.dao;

import tech.kaijie.domain.User;

import java.util.List;

public interface UserDao{

    /**
     * 查询用户下的所有角色信息
     * @return
     */
    List<User> findAllUser();

}
