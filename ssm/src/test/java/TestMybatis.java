import com.tc.dao.*;
import com.tc.domain.*;
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

        User byId = mapper.findById(8);

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

    @Test
    public void test5() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setPassword("213123123");
        user.setUsername("213123213213");
        user.setStudentId("213123");
        user.setId(7);
        user.setIcon("dist/static/user_icon/no_user.jpg");
        user.setQQ("test update username");
        mapper.updateUser(user);
        sqlSession.commit();
    }

    @Test
    public void test6() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> rankLimitOrderByPassNum = mapper.findRankLimitOrderByPassNum(5);
        System.out.println(rankLimitOrderByPassNum.toString());
        sqlSession.commit();
    }
    @Test
    public void test7() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        BbsDao mapper = sqlSession.getMapper(BbsDao.class);
        BBS rankLimitOrderByPassNum = mapper.findBBSById(11);
        System.out.println(rankLimitOrderByPassNum.toString());
        sqlSession.commit();
    }
    @Test
    public void test8() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        User_BBSDao mapper = sqlSession.getMapper(User_BBSDao.class);
        List<User_BBS> list = mapper.findUser_BBSByBBSId(8);
        for (User_BBS user_bbs:list){
            System.out.println(user_bbs.getUser().toString());
            System.out.println(user_bbs.getBbs().toString());
        }
        sqlSession.commit();
    }
    @Test
    public void test9() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        BbsDao mapper = sqlSession.getMapper(BbsDao.class);
        mapper.findBBSLimit(1,50);
        sqlSession.commit();
    }
    @Test
    public void test10() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        Competition_ProblemDao mapper = sqlSession.getMapper(Competition_ProblemDao.class);
        List<Competition_Problem> list = mapper.findCompetition_ProblemsByCompetitionId(1);
        for (Competition_Problem c:list){
            System.out.println(c);
        }
    }
}
