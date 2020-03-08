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
     * 测试查询所有账户，同时包含用户名和地址（方式二）
     */
    @Test
    public void testFindAccountAll2()
    {
        List<Account> accounts = accountDao.findAllAccount2();
//        for (Account account : accounts)
//        {
//            System.out.println("-----每个Account信息-------");
//            System.out.println(account);
//            System.out.println(account.getUser());
//        }

    }
}
