package com.cskaoyan.mall;

public class ss {
    private static ss ourInstance = new ss();

    public static ss getInstance() {
        return ourInstance;
    }

    private ss() {
    }
}
