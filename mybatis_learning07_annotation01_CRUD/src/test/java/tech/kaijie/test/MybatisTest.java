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

public class MybatisTest {
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
     * 测试增加用户
     */
    @Test
    public void testSaveUser()
    {
        User user = new User();
        user.setUserName("增加用户");
        user.setUserBirthday(new Date());
        user.setUserSex("男");
        user.setUserAddress("香港");

        int count = userDao.saveUser(user);
        System.out.println(count);
    }

    /**
     * 测试删除用户
     */
    @Test
    public void testDeleteUser()
    {
        int count = userDao.deleteUser(58);
        System.out.println(count);
    }

    /**
     * 测试修改用户信息
     */
    @Test
    public void testUpdateUser()
    {
        User user = userDao.findByUserId(41);
        user.setUserName("kaijie234");
        int count = userDao.updateUser(user);
        System.out.println(count);
    }


    /**
     * 测试查询所有用户信息
     */
    @Test
    public void testFindAll()
    {
        List<User> users = userDao.findAll();
        for (User user : users)
            System.out.println(user);
    }

    /**
     * 测试根据用户id查询用户信息
     */
    @Test
    public void testFindByUserId()
    {
        User user = userDao.findByUserId(41);
        System.out.println(user);
    }

    /**
     * 测试根据username进行模糊查询
     */
    @Test
    public void testFindByUserName()
    {
        List<User> users = userDao.findByUserName("%王%");
        for (User user : users)
            System.out.println(user);
    }

    /**
     * 测试查询中使用聚合函数
     */
    @Test
    public void testFindUserTotal()
    {
        int totalUser = userDao.findUserTotal();
        System.out.println(totalUser);
    }
}
