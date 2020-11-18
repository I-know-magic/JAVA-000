package test;

import bytebuddy.agentdemo.TestMain;
import org.junit.Test;

public class AgentTest {
    public static void main(String[] args)  throws Exception{
        TestMain testMain=new TestMain();
        testMain.printB();
        testMain.printA();
    }
}
