package tech.kaijie.dao;

import org.apache.ibatis.annotations.*;
import tech.kaijie.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 添加用户
     * @param user
     */
    @Insert("insert into user(username, birthday, sex, address) values(#{userName}, #{userBirthday}, #{userSex}, #{userAddress})")
    @SelectKey(keyProperty = "userId", keyColumn = "id", resultType = Integer.class, before = false, statement = {"select last_insert_id()"})
    int saveUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    @Delete("delete from user where id = #{userId}")
    int deleteUser(Integer id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Update("update user set username = #{userName}, birthday = #{userBirthday}, sex = #{userSex}, address = #{userAddress} where id = #{userId}")
    int updateUser(User user);

    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap",
            value = {
                    @Result(id = true, property = "userId", column = "id"),
                    @Result(property = "userName", column = "username"),
                    @Result(property = "userBirthday", column = "birthday"),
                    @Result(property = "userSex", column = "sex"),
                    @Result(property = "userAddress", column = "address")
            })
    List<User> findAll();

    /**
     * 根据用户id查询用户信息
     */
    @Select("select * from user where id = #{userId}")
    @ResultMap("userMap")
    User findByUserId(Integer userId);

    /**
     *  根据username进行模糊查询
     */
    @Select("select * from user where username like #{userName}")
    @ResultMap("userMap")
    List<User> findByUserName(String userName);

    /**
     * 查询中使用聚合函数
     */
    @Select("select count(*) from user")
    int findUserTotal();
}
