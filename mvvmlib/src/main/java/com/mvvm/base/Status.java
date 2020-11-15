package com.mvvm.base;


public enum Status {

    /**
     * status success
     */
    SUCCESS(0),

    /**
     * status failed
     */
    FAILED(1),

    /**
     * status loading
     */
    LOADING(2);

    public final int id;

    Status(int id) {
        this.id = id;
    }
}
