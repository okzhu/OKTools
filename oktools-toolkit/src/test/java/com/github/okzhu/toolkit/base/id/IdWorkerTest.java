package com.github.okzhu.toolkit.base.id;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class IdWorkerTest {


    private long WORKER_ID = 1;
    private long DATA_CENTER_ID = 1;

    private IdWorker ID_WORKER = new IdWorker(WORKER_ID, DATA_CENTER_ID);

    @Test
    void getDataCenterId() {
        Assert.assertEquals(DATA_CENTER_ID, ID_WORKER.getDataCenterId());
    }

    @Test
    void getWorkerId() {
        Assert.assertEquals(WORKER_ID, ID_WORKER.getWorkerId());
    }


    @Test
    void getId() {
        Assert.assertNotEquals(ID_WORKER.getId(), ID_WORKER.getId());
    }

    @Test
    void getIdTimestamp() {
        System.out.println(ID_WORKER.getIdTimestamp(313505057821822976L));
    }
}