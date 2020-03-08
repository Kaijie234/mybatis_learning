package tech.kaijie.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tech.kaijie.dao.UserDao;
import tech.kaijie.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserTest1 {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private UserDao userDao;

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
        userDao = sqlSession.getMapper(UserDao.class);
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
     * 测试查询用户下的所有角色信息
     */
    @Test
    public void testFindAllUser()
    {
        List<User> users = userDao.findAllUser();
        for (User user : users)
        {
            System.out.println("-------角色信息-----");
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }

}
