package cc.fbksy.edu.lambda;

import java.util.Arrays;
import java.util.List;

public class LambdaDemoB {
    public static void main(String[] args) {

        Dog dog = ()->{
            System.out.println("Hello");
        };

        dog.say();

        List<String> nameList = Arrays.asList("A","B","C");

        long size = nameList.stream().filter((a)->"Demo".equals(a)).count();
        System.out.println(String.valueOf(size));

        nameList.forEach(str-> System.out.println(str));

        System.out.println("函数编程");
        nameList.forEach(System.out::println);
    }

    interface  Dog{
        public void say();
    }
}
