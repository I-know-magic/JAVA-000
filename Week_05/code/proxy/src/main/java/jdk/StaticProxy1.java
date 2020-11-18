package jdk;

import bean.Person;

/**
 * 静态代理
 */
public class StaticProxy1 implements Person {
    Person person;

    public StaticProxy1(Person person) {
        this.person = person;
    }

    @Override
    public void reading() {
        System.out.println("StaticProxy1--reading-->before");
        person.reading();
        System.out.println("StaticProxy1--reading-->end");
    }

    @Override
    public void running() {
        System.out.println("StaticProxy--running-->before");
        person.running();
        System.out.println("StaticProxy--running-->end");
    }

    @Override
    public void eating() {
        System.out.println("StaticProxy--eating-->before");
        person.eating();
        System.out.println("StaticProxy--eating-->end");
    }
}
