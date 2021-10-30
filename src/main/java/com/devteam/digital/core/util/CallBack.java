package com.devteam.digital.core.util;

/**
 * @see {@link SpringContextHolder}
 * For some initialization methods, before SpringContextHolder is initialized, <br>
 * One can submit a callback task. <br>
 * SpringContextHolder After initialization, make callback use
 */

public interface CallBack {
    /**
     *Callback execution method
     */
    void executor();

    /**
     * Name of this callback task
     * @return /
     */
    default String getCallBackName() {
        return Thread.currentThread().getId() + ":" + this.getClass().getName();
    }
}
