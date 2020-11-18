package bytebuddy.agentdemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMain {
    public  void printA() throws Exception{
        log.info("print A!");
        Thread.sleep(100);
    }
    public  void printB() throws Exception{
        log.info("print B!");
        Thread.sleep(100);
    }

    public static void main(String[] args)  throws Exception{
        TestMain testMain=new TestMain();
        testMain.printB();
        testMain.printA();
    }
}
