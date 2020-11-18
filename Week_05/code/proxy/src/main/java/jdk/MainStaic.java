package jdk;

import bean.Student;

public class MainStaic {
    public static void main(String[] args) {
        System.out.println("静态代理1===》");
        new StaticProxy(new Student()).reading();
        System.out.println("静态代理2，嵌套===》");
        new StaticProxy(new StaticProxy1(new Student())).reading();
    }
}
