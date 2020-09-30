package com.lhl.boot;

import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.*;
import io.vavr.control.Try;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-09-03
 * @time 15:43
 * @describe:
 */
@RunWith(JUnit4.class)
public class CommonTest {


    @Test
    public void newCollectionsTest() {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,1,2,1,1,1);
        HashMultiset<Integer> multiset = HashMultiset.create(list);
        int count = multiset.count(1);
        System.out.println(count);

        ListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        String key1 = "key1";
        IntStream.range(1, 5).forEach(num -> multimap.put(key1, num));
        System.out.println(multimap.get(key1));


        BiMap<String, Integer> biMap = HashBiMap.create();
        String key2 = "key2";
        IntStream.range(1, 5).forEach(num -> biMap.put(key2 + num, num));
        System.out.println(biMap);
        System.out.println(biMap.inverse());

        ImmutableSet digits = ImmutableSet.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            public Integer apply(String string) {
                return string.length();
            }
        };
        ImmutableListMultimap<Integer, String> digitsByLength= Multimaps.index(digits, lengthFunction);
        System.out.println(digitsByLength);
    }

    @Test
    public void futureTest() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> getRet(100), executorService);
        CompletableFuture[] completableFutures = IntStream.range(1, 100)
                .boxed()
                .map(num -> CompletableFuture.supplyAsync(() -> getRet(num), executorService))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).join();
        List<Object> list = Arrays.stream(completableFutures)
                .map(item -> {
                    try {
                        return item.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
        System.out.println(list);
        long endTime = System.currentTimeMillis();
        System.out.println("consume:" + (endTime - startTime) + " ms");
    }

    @Test
    public void commonTest() {
        long startTime = System.currentTimeMillis();
        List<Object> collect = IntStream.range(1, 100).boxed().map(item -> getRetByArg(item)).collect(Collectors.toList());
        System.out.println(collect);
        long endTime = System.currentTimeMillis();
        System.out.println("consume:" + (endTime - startTime) + " ms");
    }

    private String getRet(Integer arg){
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ret" + arg;
    }

    private Integer getRetByArg(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return time;
    }

    @Test
    public void CompletableFutureTest() {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture[] completableFutures = IntStream.range(1, 100)
                .boxed()
                .map(num -> CompletableFuture.supplyAsync(() -> getRetByArg(num), executorService))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).join();
        List<Object> list = Arrays.stream(completableFutures)
                .map(item -> {
                    try {
                        if(new Random().nextBoolean() == true) {
                            throw new RuntimeException();
                        }
                        return item.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
        System.out.println(list);
        long endTime = System.currentTimeMillis();
        System.out.println("consume:" + (endTime - startTime) + " ms");
    }

}
