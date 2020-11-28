package com.lhl.boot.utils;

import com.google.common.base.Preconditions;

/**
 * 来自于twitter项目<a
 * href="https://github.com/twitter/snowflake">snowflake</a>的id产生方案，全局唯一，时间有序
 * <p/>
 * from http://bucketli.iteye.com/blog/1057855
 *
 * @author boyan
 * @Date 2011-4-27
 * <p/>
 * <p/>
 * •id is composed of: ◦time - 41 bits (millisecond precision w/ a custom
 * epoch gives us 69 years) ◦configured machine id - 10 bits - gives us up
 * to 1024 machines ◦sequence number - 12 bits - rolls over every 4096 per
 * machine (with protection to avoid rollover in the same ms)
 */
public class IdWorkerUtil {

    private final long workerId;

    // 2011-11-08
    static final long twepoch = 1320681600000L;

    private long sequence = 0L;

    static final long workerIdBits = 10L;

    static final long maxWorkerId = -1L ^ -1L << workerIdBits;

    static final long sequenceBits = 12L;

    static final long workerIdShift = sequenceBits;

    static final long timestampLeftShift = sequenceBits + workerIdBits;

    static final long sequenceMask = -1L ^ -1L << sequenceBits;

    private long lastTimestamp = -1L;

    private volatile static IdWorkerUtil instance = null;

    private IdWorkerUtil(long workerId) {
        Preconditions.checkArgument(workerId < maxWorkerId && workerId >= 0
                , "worker Id was %s but it could not be greater than %s or less than 0"
                , workerId, maxWorkerId);
        this.workerId = workerId;
    }


    public static IdWorkerUtil getInstance() {
        return getInstance(getIpSum());
    }

    public static long getIpSum() {
        long id = 0;
        if (instance == null) {
            String ip = AddressHelper.getLocalhostIPV4();
            if (!ip.equals(AddressHelper.IPV4_ERROR)) {
                String[] strs = ip.split("\\.");
                for (String s : strs) {
                    id += Integer.parseInt(s);
                }
            } else {
                id = System.currentTimeMillis() % 1024;
            }
        }
        return id;
    }

    /**
     * @param workerId 不能超過1023, 不能小于0
     * @return
     */
    public static IdWorkerUtil getInstance(long workerId) {
        if (instance == null) {
            synchronized (IdWorkerUtil.class) {
                if (instance == null) {
                    instance = new IdWorkerUtil(workerId);
                }
            }
        }
        return instance;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1 & sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
        }

        this.lastTimestamp = timestamp;
        return timestamp - twepoch << timestampLeftShift
                | workerId << workerIdShift | this.sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

}

