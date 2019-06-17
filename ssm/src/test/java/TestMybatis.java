import com.tc.dao.AccountDao;
import com.tc.dao.UserDao;
import com.tc.domain.Account;
import com.tc.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {


    @Test
    public void test1() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        AccountDao mapper = sqlSession.getMapper(AccountDao.class);

        List<Account> all = mapper.findAll();
        for (Account a:all){

            System.out.println(a);
        }
//        System.out.println();

    }

    @Test
    public void test2() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        AccountDao mapper = sqlSession.getMapper(AccountDao.class);

        Account account = new Account();
        account.setMoney(2333);
        account.setName("yang");
        mapper.saveAccount(account);

        sqlSession.commit();
//        System.out.println();

    }


    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        User byId = mapper.findById(1);

        System.out.println(byId);
    }

    @Test
    public void test4() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setPassword("213123123");
        user.setUsername("213123213213");
        user.setStudentId("213123");
        mapper.addUser(user);
        sqlSession.commit();
    }
}
