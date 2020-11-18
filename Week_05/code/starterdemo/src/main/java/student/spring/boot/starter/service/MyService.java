package student.spring.boot.starter.service;

public class MyService {
    private String myName;
    private int myAge;

    public int getMyAge() {
        return myAge;
    }

    public void setMyAge(int myAge) {
        this.myAge = myAge;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }
    public String queryName(){
        return "the name is "+getMyName()+",age is "+ getMyAge();
    }

    public MyService() {

    }
}
