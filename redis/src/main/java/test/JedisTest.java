package test;

import jedisDao.JedisDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 王俊 on 2019/8/20.
 */
public class JedisTest {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-jedis.xml");
        JedisDao jedisDao = (JedisDao) ac.getBean("jedisDaoImpl");
        String result = jedisDao.set("name","gouwa");
        String value = jedisDao.get("name");
        System.out.println(result);
        System.out.println(value);
    }

}
