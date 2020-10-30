package com.lhl.boot;

import org.apache.commons.compress.utils.Lists;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Time;
import java.time.Duration;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-10-30
 * @time 10:53
 * @describe:
 */
public class ReactorTest {

    @Test
    public void MonoTest() {
        Mono<Object> empty = Mono.empty();
        Mono<Object> never = Mono.never();
        Mono<String> just = Mono.just("Hello");
        Mono<Object> error = Mono.error(new RuntimeException());
    }

    @Test
    public void FluxTest() {
        Flux<Object> empty = Flux.empty();
        Flux<Object> never = Flux.never();
        Flux<String> just = Flux.just("Hello", "World");
        Flux<String> stringFlux = Flux.fromIterable(Arrays.asList("123", "456"));
        Flux<Object> error = Flux.error(new RuntimeException());
        Flux<Long> take = Flux.interval(Duration.ofMillis(100)).take(10);
        Flux.range(1,10).subscribe(System.out::println);
    }
}
