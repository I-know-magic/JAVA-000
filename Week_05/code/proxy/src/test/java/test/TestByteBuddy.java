package test;

import bytebuddy.classdemo.HelloByteBuddy;
import bytebuddy.aopdemo.UserAopV1;
import bytebuddy.aopdemo.UserAopV2;
import org.junit.Test;

public class TestByteBuddy {

    @Test
    public void testhello() throws Exception{
        HelloByteBuddy helloWord = new HelloByteBuddy();
        String hello = helloWord.hello();
        System.out.println(hello);
    }
    @Test
    public void testproxy() throws Exception{
        HelloByteBuddy helloWord = new HelloByteBuddy();
        helloWord.byteBuddyDemo();
    }
    @Test
    public void testAop() throws Exception{
        final UserAopV1 userAopV1 = new UserAopV1();
        userAopV1.testQueryUserById(TestByteBuddy.class.getClassLoader());
    }
    @Test
    public void testAopV2() throws Exception{
        final UserAopV2 userAopV2 = new UserAopV2();
        userAopV2.testQueryUserById(TestByteBuddy.class.getClassLoader());
    }
}
