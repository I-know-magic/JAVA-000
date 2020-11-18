package bean;

public class Student1 implements Person {
    @Override
    public void reading() {
        System.out.println("student1->reading");
    }

    @Override
    public void running() {
        System.out.println("student1->running");

    }

    @Override
    public void eating() {
        System.out.println("student1->eating");
    }
}
