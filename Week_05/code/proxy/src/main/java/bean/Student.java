package bean;

public class Student implements Person {
    @Override
    public void reading() {
        System.out.println("student->reading");
    }

    @Override
    public void running() {
        System.out.println("student->running");

    }

    @Override
    public void eating() {
        System.out.println("student->eating");
    }
}
