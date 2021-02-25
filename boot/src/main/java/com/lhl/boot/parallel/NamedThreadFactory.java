package com.lhl.boot.parallel;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class NamedThreadFactory implements ThreadFactory {
    private final String name;
    private boolean isDaemon = false;
    private final AtomicLong threadNum = new AtomicLong(0);

    public NamedThreadFactory(String name) {
        this.name = name;
    }

    public NamedThreadFactory(String name, boolean isDaemon) {
        this.name = name;
        this.isDaemon = isDaemon;
    }

    public synchronized Thread newThread(Runnable runnable) {
        Thread t = Executors.defaultThreadFactory().newThread(runnable);
        t.setName(this.name + "-" + this.threadNum.getAndIncrement() + "-");
        t.setDaemon(this.isDaemon);
        return t;
    }

    public static void setNamedThreadName(Thread thread, String name) {
        final String threadName = thread.getName();
        final String threadNamePrefix = threadName.substring(0, threadName.lastIndexOf("-") + 1);
        thread.setName(threadNamePrefix + name);
    }
}