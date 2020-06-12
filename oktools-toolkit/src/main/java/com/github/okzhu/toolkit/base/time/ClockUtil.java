package com.github.okzhu.toolkit.util.time;

import java.util.Date;

/**
 * 日期提供者, 使用它而不是直接取得系统时间, 方便测试.
 * 原因是因为 我们有一个需求是 我们的促销活动
 * 在规定的时间内。会给与一定的优惠。但是 当我们测试的时候 需要去修改系统时间来测试。
 * 这个就导致了这个类的主要动机了。
 * <p>
 * 我们系统 所有的 获取时间的在这个类里面来获取。那么我就可以直接通过代码修改时间来测试
 **/
public class ClockUtil {


    public interface Clock {

        /**
         * 系统当前时间
         */
        Date currentDate();

        /**
         * 系统当前时间戳
         */
        long currentTimeMillis();

        /**
         * 操作系统启动到现在的纳秒数，与系统时间是完全独立的两个时间体系
         */
        long nanoTime();
    }


    private static Clock instance = new DefaultClock();

    /**
     * 计算流逝的时间
     */
    public static long elapsedTime(long beginTime) {
        return currentTimeMillis() - beginTime;
    }

    /**
     * 切换为DummyClock，使用系统时间为初始时间, 单个测试完成后需要调用useDefaultClock()切换回去.
     */
    public static synchronized DummyClock useDummyClock() {
        instance = new DummyClock();
        return (DummyClock) instance;
    }

    /**
     * 切换为DummyClock，单个测试完成后需要调用useDefaultClock()切换回去.
     */
    public static synchronized DummyClock useDummyClock(long timeStampMills) {
        instance = new DummyClock(timeStampMills);
        return (DummyClock) instance;
    }

    /**
     * 切换为DummyClock，单个测试完成后需要调用useDefaultClock()切换回去.
     */
    public static synchronized DummyClock useDummyClock(Date date) {
        instance = new DummyClock(date);
        return (DummyClock) instance;
    }

    /**
     * 重置为默认Clock
     */
    public static synchronized void useDefaultClock() {
        instance = new DefaultClock();
    }

    /**
     * 系统当前时间
     */
    public static Date currentDate() {
        return instance.currentDate();
    }

    /**
     * 系统当前时间戳
     */
    public static long currentTimeMillis() {
        return instance.currentTimeMillis();
    }

    /**
     * 操作系统启动到现在的纳秒数，与系统时间是完全独立的两个时间体系
     */
    public static long nanoTime() {
        return instance.nanoTime();
    }


    /**
     * 默认时间提供者，返回当前的时间，线程安全。
     */
    public static class DefaultClock implements Clock {

        @Override
        public Date currentDate() {
            return new Date();
        }

        @Override
        public long currentTimeMillis() {
            return System.currentTimeMillis();
        }

        @Override
        public long nanoTime() {
            return System.nanoTime();
        }
    }

    /**
     * 可配置的时间提供者，用于测试.
     */
    public static class DummyClock implements Clock {

        private long time;
        private long nanoTme;

        public DummyClock() {
            this(System.currentTimeMillis());
        }

        public DummyClock(Date date) {
            this(date.getTime());
        }

        public DummyClock(long time) {
            this.time = time;
            this.nanoTme = System.nanoTime();
        }

        @Override
        public Date currentDate() {
            return new Date(time);
        }

        @Override
        public long currentTimeMillis() {
            return time;
        }

        /**
         * 获取nanoTime
         */
        @Override
        public long nanoTime() {
            return nanoTme;
        }

        /**
         * 重新设置日期.
         */
        public void updateNow(Date newDate) {
            time = newDate.getTime();
        }

        /**
         * 重新设置时间.
         */
        public void updateNow(long newTime) {
            this.time = newTime;
        }

        /**
         * 滚动时间.
         */
        public void increaseTime(int millis) {
            time += millis;
        }

        /**
         * 滚动时间.
         */
        public void decreaseTime(int millis) {
            time -= millis;
        }

        /**
         * 设置nanoTime.
         */
        public void setNanoTime(long nanoTime) {
            this.nanoTme = nanoTime;
        }
    }


}
