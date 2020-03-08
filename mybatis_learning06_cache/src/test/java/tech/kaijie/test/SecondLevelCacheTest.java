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

public class SecondLevelCacheTest {
    private InputStream in;

    private SqlSessionFactory factory;

    @Before //用于在测试方法执行之前执行
    public void init() throws Exception
    {
        // 1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.获取SqlSessionFactory对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
    }

    @After //用于在测试方法执行之后执行
    public void destroy() throws Exception {
        in.close();
    }

    /**
     * 测试二级缓存
     */

    @Test
    public  void testFirstLevelCache()
    {
        SqlSession sqlSession1 = factory.openSession();
        UserDao dao1 = sqlSession1.getMapper(UserDao.class);
        User user1 = dao1.findByUserId(41);
        System.out.println("user1 = " + user1);
        sqlSession1.close();    // 关闭一级缓存

        SqlSession sqlSession2 = factory.openSession();
        UserDao dao2 = sqlSession2.getMapper(UserDao.class);
        User user2 = dao2.findByUserId(41);
        System.out.println("user2 = " + user2);
        sqlSession2.close();

        // 在执行第一次查询后，我们关闭了一级缓存，再去执行第二次查询时
        // 我们发现并没有对数据库发出 sql 语句，所以此时的数据就只能是来自于我们所说的二级缓存。
        System.out.println(user1 == user2); // 输出false，是因为二级缓存中存放的内容是数据，而不是对象
    }
}

