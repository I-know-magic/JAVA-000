package demoioc.ioc;

import java.util.Map;

/**
 * 模拟下IOC，bean产生过程，加深理解。
 * ID CLASS find bean
 * 依赖注入，前置操作后置操作，
 * BeanFactory，beanName
 * 观察者模式，解耦
 * 监听器，不同阶段发出不同事件
 * 扩展性：抽象，设计模式
 * 基本思想：高内聚，低耦合，扩展性，变化的抽接口，相同的多模版
 * aop 抽象出一个接口
 */
public class ContainerDemo {
    //    通过抽象接口给容器塞入数据
    Map<String, BeanInfoDemo> map;//id bean信息
    //对象信息class.getConstructor.newInstance 通过工厂类创建
//    普通对象
    Map<Class, BeanFactoryDemo> mapClass;//对象信息class.getConstructor.newInstance
//    容器类对象
//    BeanProccesorDemo
//    BeanFactoryPostProccesor
}
