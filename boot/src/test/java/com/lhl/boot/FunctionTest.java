package com.lhl.boot;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import org.junit.Test;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-09-04
 * @time 15:09
 * @describe:
 */
public class FunctionTest {

    @Test
    public void ConsumerTest() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Consumer consumer1 = s -> System.out.println(s);
        Consumer consumer2 = System.out::println;
        IntStream.range(1, 5).boxed().forEach(consumer1);
    }


    @Test
    public void SupplierTest() {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt();
            }
        };
        Supplier supplier1 = () -> new Random().nextInt();
        Supplier<Double> supplier2 = Math::random;
        System.out.println(supplier.get());
    }

    @Test
    public void PredicateTest() {
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 0;
            }
        };
        Predicate<Integer> predicate1 = num -> num > 0;
        System.out.println(predicate.test(1));
    }

    @Test
    public void Function0Test() {
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        System.out.println(function.apply("Hello World!"));
    }

    @Test
    public void VavrFunctionTest() {
        Function0<String> function0 = () -> "HelloWorld!";
        Function1<String, String> function1 = str -> str;
        Function2<String, String, String> function2 = (hello, world) -> hello + world;
        String hello = function2.apply("Hello", "World!");
        System.out.println(hello);
    }
}
