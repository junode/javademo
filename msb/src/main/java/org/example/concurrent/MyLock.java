package org.example.concurrent;

/**
 * 实现AQS锁
 * 支持重入
 */
public class MyLock {
    private Sync sync;

    class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            /**
             * state 含义
             * 0 表示没有线程持有锁
             * 1 表示有线程持有锁
             */
            int c = getState();
            if (c == 0) {
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            } else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                setState(nextc);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int releases) {
            int c = getState() - releases;
            if(Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalArgumentException();
            }
            boolean free = false;
            if(c==0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }
        public void showLockThread() {
            if(getExclusiveOwnerThread() != null) {
                System.out.println("current thread : " + getExclusiveOwnerThread().getName());
            }
        }
    }
    MyLock() {
        this.sync = new Sync();
    }
    public void lock() {
        sync.acquire(1);
    }
    public void unlock() {
        sync.release(1);
    }
    public void showLockThread() {
        sync.showLockThread();
    }

    public static void main(String[] args) throws InterruptedException {
        MyLock myLock = new MyLock();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 start ...");
                myLock.lock();
                System.out.println("t1 get lock ...");
                Thread.sleep(3000);
                myLock.lock();
                System.out.println("t1 get lock again ...");
                System.out.println("t1 return ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                // 上锁两次，相应释放锁两次
                myLock.unlock();
                myLock.unlock();
            }
        });
        t1.setName("t1");
        Thread t2 = new Thread(()->{
            try {
                System.out.println("t2 start ...");
                myLock.lock();
                System.out.println("t2 get lock ...");
                Thread.sleep(3000);
                System.out.println("t2 return ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                myLock.unlock();
            }
        });
        t2.setName("t2");
        t1.start();
        t2.start();
        Thread.sleep(2000);
        myLock.showLockThread();
        t1.join();
        t2.join();
        System.out.println("main thread exit...");
    }
}
