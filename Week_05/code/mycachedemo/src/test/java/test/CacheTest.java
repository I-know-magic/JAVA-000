package test;

import com.excample.Application;
import com.excample.controller.UserController;
import com.excample.test.UserTest;
import com.excample.utils.RedisUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CacheTest {

    @Resource
    UserTest userTest;
    @Resource
    UserController userController;
    @Resource
    RedisUtil redisUtil;
    long id=1000;
    String name="lvpeng";
    @Test
    public void testCache(){
        userController.testUser(id);
    }

    @Test
    public void testQueryCache(){
        Object o=redisUtil.get(name);
        Assert.assertNotNull("缓存有效",o);

        System.out.println(o.toString());
    }
}
