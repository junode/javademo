package org.example.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁基本实例
 */
public class ReentrantReadWriteLockCache {
    // 定义一个非线程安全的HashMap用于缓存对象
    static Map<String, Object> map = new HashMap<String, Object>();
    // 创建读写锁对象
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 构建读锁
    static Lock rl = readWriteLock.readLock();
    // 构建写锁
    static Lock wl = readWriteLock.writeLock();

    public static final Object get(String key) {
        rl.lock();
        try {
            return map.get(key);
        } finally {
            rl.unlock();
        }
    }

    public static final void pug(String key, String value) {
        wl.lock();
        try {
            map.put(key, value);
        } finally {
            wl.unlock();
        }
    }
}
