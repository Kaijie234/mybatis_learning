package tech.kaijie.dao;

import tech.kaijie.domain.QueryVo;
import tech.kaijie.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 动态Sql之if标签
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 动态Sql之where标签
     * @param user
     * @return
     */
    List<User> findUserByCondition2(User user);

    /**
     * 动态Sql之foreach标签
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);

    /**
     * 使用简化编写的sql片段进行查询所有操作
     */
    List<User> findAll();
}
