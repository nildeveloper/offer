package com.lhl.boot.cache;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-11-28
 * @time 15:00
 * @describe:
 */
public abstract class AbstractCache<T> {

    protected String key;

    private static LoadingCache<AbstractCache, Optional> LOADING_CACHE = CacheBuilder
            .newBuilder()
            .refreshAfterWrite(1, TimeUnit.MINUTES)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(new CacheLoader<AbstractCache, Optional>() {
                @Override
                public Optional load(AbstractCache key) {
                    try {
                        return Optional.fromNullable(key.loadCache());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return Optional.absent();
                }
            });

    /**
     * 抽象方法，需子类实现
     * @return
     */
    public abstract T loadCache();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractCache) {
            return this.key.equals(((AbstractCache) obj).key);
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    @SuppressWarnings("unchecked")
    public T get() {
        try {
            Optional<T> optional = LOADING_CACHE.get(this);
            if (optional.isPresent()) {
                return optional.get();
            } else {
                return this.loadCache();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

