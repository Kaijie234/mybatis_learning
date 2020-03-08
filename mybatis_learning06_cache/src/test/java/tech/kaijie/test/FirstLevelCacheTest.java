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

import java.io.InputStream;
import java.util.List;

public class FirstLevelCacheTest {
    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;
    private SqlSessionFactory factory;

    @Before //用于在测试方法执行之前执行
    public void init() throws Exception
    {
        // 1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.获取SqlSessionFactory对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        // 3.获取SqlSession对象
        sqlSession = factory.openSession();
        // 4.获取dao的代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After //用于在测试方法执行之后执行
    public void destroy() throws Exception {
        // 提交事务
//        sqlSession.commit();

        // 6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll()
    {
        List<User> users = userDao.findAll();
        for (User user : users)
        {
            System.out.println("---每个user信息---");
            System.out.println(user);
        }
    }

    /**
     * 测试一级缓存1
     */

    @Test
    public  void testFirstLevelCache()
    {
        User user1 = userDao.findByUserId(41);
        System.out.println(user1);
        User user2 = userDao.findByUserId(41);
        System.out.println(user2);

        // 输出为true，证明存在一级缓存
        System.out.println(user1 == user2);
    }

    /**
     * 测试一级缓存2
     */

    @Test
    public  void testFirstLevelCache2()
    {
        User user1 = userDao.findByUserId(41);
        System.out.println("user1" + user1);

        sqlSession.close();
        // 再次获取Sqlsession对象
//        sqlSession.clearCache(); // 此方法也可以清空一级缓存
        SqlSession sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);

        User user2 = userDao.findByUserId(41);
        System.out.println("user2: " + user2);

        // 输出为false，证明存在使用close或clearCache时，会清空一级缓存
        System.out.println(user1 == user2);
    }

    /**
     * 测试一级缓存的同步
     * 如果 sqlSession 去执行 commit 操作（执行插入、更新、删除），
     * 清空 SqlSession 中的一级缓存，
     * 这样做的目的为了让缓存中存储的是最新的信息，避免脏读。
     */

    @Test
    public  void testClearCache()
    {
        // 1.根据id查询用户
        User user1 = userDao.findByUserId(41);
        System.out.println(user1);

        // 2.更新用户信息
        user1.setUsername("update user clear cache");
        user1.setAddress("中国");
        userDao.updateUser(user1);
        sqlSession.commit();

        // 3.再次查询id为41的用户
        User user2 = userDao.findByUserId(41);
        System.out.println(user2);

        // 输出为false
        System.out.println(user1 == user2);
    }
}

