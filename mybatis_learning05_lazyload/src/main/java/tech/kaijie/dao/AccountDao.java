package tech.kaijie.dao;

import tech.kaijie.domain.Account;
import java.util.List;

/**
 * Account的持久层接口
 */
public interface AccountDao {

    /**
     * 查询所有账户，同时获取账户的所属用户名称以及它的地址信息
     * @return
     */
    List<Account> findAll();

    /**
     * 根据账户 id 查询账户信息和对应的用户信息
     * @param uid
     * @return
     */
    List<Account> findByUid(Integer uid);
}