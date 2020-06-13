package com.github.okzhu.toolkit.base.id;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by kaiqian.zhu on 2018/2/1.
 * 注意 闰秒回拨
 */
public class IdWorker {

    private static final long WORKER_ID_BITS = 5L;
    private static final long DATACENTER_ID_BITS = 5L;
    private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);
    private static final long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_ID_BITS);
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;
    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);
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
        this(r.nextInt((int) MAX_WORKER_ID), r.nextInt((int) MAX_DATACENTER_ID), 0, idEpoch);
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
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException("workerId is illegal: " + workerId);
        }
        if (dataCenterId < 0 || dataCenterId > MAX_DATACENTER_ID) {
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
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;

        return ((timestamp - idEpoch) << TIMESTAMP_LEFT_SHIFT)//
                | (dataCenterId << DATACENTER_ID_SHIFT)//
                | (workerId << WORKER_ID_SHIFT)//
                | sequence;
    }

    /**
     * get the timestamp (millis second) of id
     *
     * @param id the nextId
     * @return the timestamp of id
     */
    public long getIdTimestamp(long id) {
        return idEpoch + (id >> TIMESTAMP_LEFT_SHIFT);
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
