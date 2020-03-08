package tech.kaijie.dao;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import tech.kaijie.domain.Account;
import java.util.List;

/**
 * Account的持久层接口
 */
public interface AccountDao {

    /**
     * 查询所有账户，采用延迟加载的方式查询账户的所属用户
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            @Result(column = "uid", property = "user", one = @One(select = "tech.kaijie.dao.UserDao.findById", fetchType = FetchType.LAZY))
    })
    List<Account> findAllAccount2();

    /**
     * 根据id查询账户信息
     * @return
     */
    @Select("select * from account where uid = #{id}")
    List<Account> findByUid();
}