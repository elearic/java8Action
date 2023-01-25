package ericzz.reflect;

public class ReflectMain {

    public static void main(String[] args) throws ClassNotFoundException {
        Apple apple = new Apple();
        Class<? extends Apple> aClass = apple.getClass();
        System.out.println(aClass.getName());

        Class<?> aClass1 = Class.forName("ericzz.reflect.Apple");
        System.out.println(aClass1.getName());

        Class<Apple> appleClass = Apple.class;
        System.out.println(appleClass.getName());
    }
}
