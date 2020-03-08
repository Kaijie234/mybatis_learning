package tech.kaijie.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tech.kaijie.dao.UserDao;
import tech.kaijie.domain.QueryVo;
import tech.kaijie.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
     * 测试动态Sql之if标签
     */
    @Test
    public void testFindUserByCondition()
    {
        User user = new User();
        user.setUsername("kaijie");
        user.setSex("男");

        List<User> users = userDao.findUserByCondition(user);
        for (User u : users)
            System.out.println(u);
    }

    /**
     * 测试动态Sql之where标签
     */
    @Test
    public void testFindUserByCondition2()
    {
        User user = new User();
        user.setUsername("kaijie");
        user.setSex("男");

        List<User> users = userDao.findUserByCondition2(user);
        for (User u : users)
            System.out.println(u);
    }

    /**
     * 测试动态Sql之foreach标签
     */
    @Test
    public void testFindUserInIds() {
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(43);
        vo.setIds(list);

        List<User> users = userDao.findUserInIds(vo);
        for (User user : users)
            System.out.println(user);
    }

    /**
     * 使用简化编写的sql片段进行查询所有操作
     */
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users)
            System.out.println(user);
    }
}
