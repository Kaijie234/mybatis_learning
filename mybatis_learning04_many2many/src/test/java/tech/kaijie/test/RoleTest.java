package tech.kaijie.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tech.kaijie.dao.RoleDao;
import tech.kaijie.domain.Role;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private RoleDao roleDao;

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
        roleDao = sqlSession.getMapper(RoleDao.class);
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
     * 测试查询角色下的所有用户信息
     */
    @Test
    public void testFindAllRole()
    {
        List<Role> roles = roleDao.findAllRole();
        for (Role role : roles)
        {
            System.out.println("---每个role信息---");
            System.out.println(role);
        }
    }
}
