//1117.building-h2o
//现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
//
// 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。 
//
// 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。 
//
// 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。 
//
// 你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。 
//
// 换句话说: 
//
// 
// 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。 
// 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。 
// 
//
// 书写满足这些限制条件的氢、氧线程同步代码。 
//
// 
//
// 示例 1: 
//
// 输入: "HOH"
//输出: "HHO"
//解释: "HOH" 和 "OHH" 依然都是有效解。
// 
//
// 示例 2: 
//
// 输入: "OOHHHH"
//输出: "HHOHHO"
//解释: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" 和 
//"OHHOHH" 依然都是有效解。
// 
//
// 
//
// 提示： 
//
// 
// 输入字符串的总长将会是 3n, 1 ≤ n ≤ 50； 
// 输入字符串中的 “H” 总数将会是 2n 。 
// 输入字符串中的 “O” 总数将会是 n 。 
// 
// Related Topics 多线程 👍 100 👎 0


package leetcode.editor.cn;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BuildingH2o {

    private static H2OGenerator generator = H2OGenerator.create();

    public static void main(String[] args) throws InterruptedException {
        gen(new LockH2O(), 10);
        System.out.println();
        gen(new SemaphoreH2O(), 10);
        System.out.println();
        gen(new CyclicBarrierH2O(), 10);
        System.out.println();

    }

    /**
     * 生产指定数据量的水分子
     *
     * @param h2O
     * @param n
     * @throws InterruptedException
     */
    public static void gen(IH2O h2O, int n) throws InterruptedException {
        ExecutorService hes = Executors.newCachedThreadPool();
        for (int i = 0; i < n * 2; i++) {
            hes.submit(() -> {
                try {
                    h2O.hydrogen(generator::hydrogen);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        ExecutorService oes = Executors.newCachedThreadPool();
        for (int i = 0; i < n; i++) {
            oes.submit(() -> {
                try {
                    h2O.oxygen(generator::oxygen);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-O-" + i);
        }
        hes.shutdown();
        hes.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        oes.shutdown();
        oes.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }

}


//leetcode submit region begin(Prohibit modification and deletion)


class H2O {

    private IH2O ih2O;

    public H2O(IH2O ih2O) {
        this.ih2O = ih2O;
    }

    public H2O() {
        this(new CyclicBarrierH2O());
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        ih2O.hydrogen(releaseHydrogen);
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        ih2O.oxygen(releaseOxygen);
    }

}

interface IH2O {
    void hydrogen(Runnable releaseHydrogen) throws InterruptedException;

    void oxygen(Runnable releaseOxygen) throws InterruptedException;

}

/**
 * 水分子合成器
 */
class H2OGenerator {
    /**
     * H原子计数
     */
    protected final AtomicInteger hNum = new AtomicInteger();
    /**
     * O原子计数
     */
    protected final AtomicInteger oNum = new AtomicInteger();


    private static H2OGenerator h2OGenerator = new H2OGenerator();

    public static H2OGenerator create() {
        return h2OGenerator;
    }

    /**
     * 生产氢原子
     */
    public void hydrogen() {
        System.out.print("H");
        hNum.incrementAndGet();
        if (hNum.get() > 2) {
            throw new IllegalStateException("生产故障");
        }
        tryWater();
    }

    /**
     * 生产氧原子
     */
    public void oxygen() {
        System.out.print("O");
        oNum.incrementAndGet();
        if (oNum.get() > 1) {
            throw new IllegalStateException("生产故障");
        }
        tryWater();
    }

    /**
     * 获取氢原子数量
     *
     * @return
     */
    public int getH() {
        return hNum.get();
    }


    /**
     * 获取氧原子数量
     *
     * @return
     */
    public int getO() {
        return oNum.get();
    }

    /**
     * 合成水分子
     *
     * @return
     */
    protected boolean tryWater() {
        if (hNum.get() == 2 && oNum.get() == 1) {
            hNum.set(0);
            oNum.set(0);
            System.out.print(";");
            return true;
        }
        return false;
    }

}


class LockH2O implements IH2O {

    private H2OGenerator generator = H2OGenerator.create();
    private Lock lock = new ReentrantLock();
    //h原子满足条件
    private Condition hReady = lock.newCondition();
    //o原子满足条件
    private Condition oReady = lock.newCondition();


    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (generator.getH() == 2) {//H原子已就绪，此时要么合成水分子，要么等待生产氧原子
                if (!generator.tryWater()) {//是否可以合成水分子
                    hReady.await();
                }
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            hReady.signalAll();
            oReady.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while (generator.getO() == 1) {//O原子已就绪，此时要么合成水分子，要么等待H原子
                if (!generator.tryWater()) {//是否可以合成水分子
                    oReady.await();
                }
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            hReady.signalAll();
            oReady.signalAll();
        } finally {
            lock.unlock();
        }
    }
}


class SemaphoreH2O implements IH2O {

    //实际上只需两个信号量的和为2。
    // 如果Semaphore_h=2，那么就是先释放两个H，然后释放一个O，输出结果：HHO；
    // 如果是Semaphore_o=2，那么就是先释放一个O，然后释放两个H，输出结果：OHH；
    // 如果Semaphore_h=1，Semaphore_o=1，那就是先释放一个H，再释放一个O，再释放一个H，输出结果：HOH；
    private Semaphore h = new Semaphore(2);
    private Semaphore o = new Semaphore(0);


    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        synchronized (this) {
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
        }
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        h.release(2);
    }

}

class CyclicBarrierH2O implements IH2O {
    private Semaphore h = new Semaphore(2);
    private Semaphore o = new Semaphore(1);
    private CyclicBarrier cb = new CyclicBarrier(3);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        try {
            cb.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        h.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        try {
            cb.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        o.release();
    }


}

//leetcode submit region end(Prohibit modification and deletion)

