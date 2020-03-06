package tech.kaijie.dao;

import tech.kaijie.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 添加用户
     * @param user
     */
    int saveUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    int deleteUser(Integer id);

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

    /**
     *  根据username进行模糊查询
     */
    List<User> findByUserName(String userName);

    /**
     * 查询中使用聚合函数
     */
    int findUserTotal();
}
