package tech.kaijie.dao;

import tech.kaijie.domain.Account;
import tech.kaijie.domain.AccountUser;
import java.util.List;

/**
 * Account的持久层接口
 */
public interface AccountDao {

    /**
     * 查询所有账户，同时包含用户名和地址（方式一）
     * @return
     */
    List<AccountUser> findAllAccount1();

    /**
     * 查询所有账户，同时包含用户名和地址（方式二）
     * @return
     */
    List<Account> findAllAccount2();

}