package com.lhl.boot.delay;

import lombok.Getter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-02-25
 * @time 15:07
 * @describe: DelayedAsyncTask
 */
@Getter
public class DelayedAsyncTask<T extends Runnable> implements Delayed {

    private T task;

    private long time;

    public DelayedAsyncTask(T task, long delayTime) {
        this.task = task;
        this.time = System.currentTimeMillis() + (delayTime > 0 ? delayTime : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        int diff = (int) (this.time - ((DelayedAsyncTask)o).getTime());
        return (diff > 0 ? 1 : (diff == 0 ? 0 : -1));
    }

    @Override
    public String toString() {
        return "DelayedAsyncTask{" +
                "task=" + task +
                ", time=" + time +
                '}';
    }
}
