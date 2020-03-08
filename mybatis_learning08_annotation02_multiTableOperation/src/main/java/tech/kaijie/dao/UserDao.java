package tech.kaijie.dao;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
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
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "address", column = "address"),
            @Result(column = "id", property = "accounts",
                    many = @Many(select = "tech.kaijie.dao.AccountDao.findByUid", fetchType = FetchType.LAZY))
    })
    List<User> findAllUser();

    /**
     * 根据用户id查询用户信息
     */
    @Select("select * from user where id = #{id}")
    User findById(Integer id);
}
