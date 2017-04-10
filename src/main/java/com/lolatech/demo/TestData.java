package com.lolatech.demo;

/**
 * Created by razvan.vuscan on 3/17/17.
 */
public enum TestData {
    NON_SECURE_PROTOCOL("http://"),
    DOCKER_IP("192.168.99.100"),
    DOCKER_PORT("4444"),
    WEB_DRIVER_HUB("/wd/hub"),

    HOME_PAGE_URL("https://en.wikipedia.org/wiki/Main_Page");

    private String dataBit;

    TestData(String dataBit) {
        this.dataBit = dataBit;
    }

    public String getTestDataBit() {
        return dataBit;
    }
}
