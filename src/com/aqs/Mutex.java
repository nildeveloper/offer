package com.aqs;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-18
 * Time: 9:01 PM
 * Description: Mutex 一个不可重入的互斥锁实现
 * 锁资源（AQS 里 state）状态只有 0 未锁定和 1 锁定
 */
public class Mutex implements Lock, Serializable{
    
    
    // 自定义同步器
    // sync只用实现资源state的获取-释放方式tryAcquire-tryRelelase，至于线程的排队、等待、唤醒等，由上层AQS实现
    private static class Sync extends AbstractQueuedSynchronizer {

        // 尝试获取资源，立即返回。成功返回 true 失败返回false
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;  // 限定只能为一个量
            // state 为 0 才设置为1， 不可重入
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());  // 当前线程独占资源
                return true;
            }
            return false;
        }

        // 尝试释放资源，立即返回。成功返回 true 失败返回false
        @Override
        protected boolean tryRelease(int arg) {
            assert  arg == 1;  // 限定只能为一个量
            // 双重保险，判断一下当前状态
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);  // 释放资源，放弃占有状态
            return true;
        }

        // 判断是否锁定状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }
    
    private final Sync sync = new Sync();  // 真正同步类的实现都依赖继承于AQS

    // 获取资源，直到成功为止
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    // 获取资源，要求立即返回
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
    
    // 释放资源
    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
    
    // 判断锁是否占有状态
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }
    
}
