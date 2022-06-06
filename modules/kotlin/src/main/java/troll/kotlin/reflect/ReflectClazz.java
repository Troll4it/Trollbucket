package troll.kotlin.reflect;import java.lang.reflect.Field;import java.lang.reflect.Method;/** * author : TangPeng * date : 5/30/22 15:14 * description : */public class ReflectClazz {    private Dog dog;    void initData() {        dog = new Dog();        dog.setName("拉布拉多");    }    public static void main(String[] args) {        ReflectClazz reflectClazz = new ReflectClazz();        reflectClazz.initData();        try {            Field field = reflectClazz.getClass().getDeclaredField("dog");            field.setAccessible(true);            Class<?> dogClazz = field.getType();            Method drinkMethod = dogClazz.getMethod("drink", String.class);            drinkMethod.setAccessible(true);            Object instance = dogClazz.newInstance();            drinkMethod.invoke(instance, "牛奶");        } catch (Exception e) {            e.printStackTrace();        }    }}class Dog {    private String name;    public String getName() {        return name;    }    public void setName(String name) {        this.name = name;    }    public void drink(String value) {        System.out.println(name + "value = " + value);    }}