package com.github.okzhu.toolkit.base.id;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by kaiqian.zhu on 2018/2/1.
 * 注意 闰秒回拨
 */
public class IdWorker {

    private static final long workerIdBits = 5L;
    private static final long datacenterIdBits = 5L;
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private static final long sequenceBits = 12L;
    private static final long workerIdShift = sequenceBits;
    private static final long datacenterIdShift = sequenceBits + workerIdBits;
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);
    private static final Random r = new SecureRandom();
    private final long workerId;
    private final long dataCenterId;
    private final long idEpoch;
    private long lastTimestamp = -1L;
    private long sequence;

    public IdWorker() {
        this(1517414400000L);
    }

    public IdWorker(long idEpoch) {
        this(r.nextInt((int) maxWorkerId), r.nextInt((int) maxDatacenterId), 0, idEpoch);
    }

    public IdWorker(long workerId, long dataCenterId) {
        this(workerId, dataCenterId, 0, 1517414400000L);
    }

    public IdWorker(long workerId, long dataCenterId, long sequence) {
        this(workerId, dataCenterId, sequence, 1517414400000L);
    }

    public IdWorker(long workerId, long dataCenterId, long sequence, long idEpoch) {
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
        this.idEpoch = idEpoch;
        if (workerId < 0 || workerId > maxWorkerId) {
            throw new IllegalArgumentException("workerId is illegal: " + workerId);
        }
        if (dataCenterId < 0 || dataCenterId > maxDatacenterId) {
            throw new IllegalArgumentException("dataCenterId is illegal: " + workerId);
        }
        if (idEpoch >= System.currentTimeMillis()) {
            throw new IllegalArgumentException("idEpoch is illegal: " + idEpoch);
        }
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public long getId() {
        return nextId();
    }

    private synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards.");
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;

        return ((timestamp - idEpoch) << timestampLeftShift)//
                | (dataCenterId << datacenterIdShift)//
                | (workerId << workerIdShift)//
                | sequence;
    }

    /**
     * get the timestamp (millis second) of id
     *
     * @param id the nextId
     * @return the timestamp of id
     */
    public long getIdTimestamp(long id) {
        return idEpoch + (id >> timestampLeftShift);
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

}
