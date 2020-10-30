package com.lhl.boot;

import io.vavr.control.Try;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-09-30
 * @time 15:23
 * @describe:
 */
public class StreamTest {

    @Test
    public void streamTest() {
        Stream<String> generate = Stream.generate((() -> "Hello World!"));
        generate.forEach(System.out::println);

        Path path = Paths.get("D:\\test.txt");
        try(Stream<String> stream = Files.lines(path)) {
            stream.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
