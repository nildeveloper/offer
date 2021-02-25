package com.lhl.boot.delay;

import lombok.Getter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-02-25
 * @time 14:26
 * @describe: DelayedTask
 */
@Getter
public class DelayedTask implements Delayed {

    private String name;

    /**
     * 延迟时间 Millis
     */
    private long time;

    public DelayedTask(String name, long delayTime) {
        this.name = name;
        this.time = System.currentTimeMillis() + (delayTime > 0 ? delayTime : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        int diff = (int) (this.time - ((DelayedTask)o).getTime());
        if (diff > 0) {
            return 1;
        } else if (diff < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "DelayedTask{" +
                "name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
