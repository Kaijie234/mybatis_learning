package tech.kaijie.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tech.kaijie.dao.AccountDao;
import tech.kaijie.domain.Account;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private AccountDao accountDao;

    /**
     * 在test之前执行
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        // 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        // 使用工厂生产SqlSession对象
        sqlSession = factory.openSession();
        // 创建代理对象
        accountDao = sqlSession.getMapper(AccountDao.class);
    }


    /**
     * 在测试之后执行
     * @throws IOException
     */
    @After
    public void destroy() throws IOException {
        // 提交SqlSession
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试根据账户 id 查询账户信息和对应的用户信息（延迟加载）
     */
    @Test
    public void testFindAll()
    {
        // 当使用延迟加载后，可以发现只要不使用accounts时，
        // 只会执行了Preparing: select * from account
        // 没有执行查询user的sql。即按需查询
        List<Account> accounts = accountDao.findAll();
//        for (Account account : accounts)
//        {
//            System.out.println("----每个账户信息-------");
//            System.out.println(account);
//            System.out.println(account.getUser());
//        }
    }
}
