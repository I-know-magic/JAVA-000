package demo.jvm;


import cn.hutool.core.io.file.FileReader;

import java.lang.reflect.Method;

public class ClassLoad1 extends ClassLoader{
    protected static final String pathHello="D:\\极客时间\\java训练营\\01-jvm\\作业资料\\Hello\\Hello.class";
    protected static final String pathHelloX="D:\\极客时间\\java训练营\\01-jvm\\作业资料\\Hello\\Hello.xlass";
    @Override
    protected Class<?> findClass(String name) {

        try {
            byte[] resultHelloX=getHelloBytes(pathHelloX);
            byte[] result=new byte[resultHelloX.length];
            for (int i = 0; i < resultHelloX.length; i++) {
                result[i]=(byte)(255-resultHelloX[i]);
            }
            return defineClass(name, result, 0, result.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getHelloBytes(String path){
		//hutool
        FileReader fileReader= new FileReader(path);
        byte[] result  = fileReader.readBytes();
        return result.length==0?null:result;
    }
    public static void main(String[] args) {
        try {
            Class hello=new ClassLoad1().findClass("Hello");
            Object object = hello.newInstance();
//            Method[] methods=hello.getDeclaredMethods();
//            for (int i = 0; i < methods.length; i++) {
//                System.out.println(methods[i].getName());
//            }
//            Method method=hello.getDeclaredMethod("hello",null);
            Method method=hello.getMethod("hello",null);

            method.invoke(object,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
