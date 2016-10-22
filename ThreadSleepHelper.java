package com.company;

class ThreadSleepHelper {
    private static int DEFAULT_SLEEP_TIME = 5000;

    static void sleep() {
        try {
            Thread.sleep(DEFAULT_SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void sleep(int period) {
        try {
            Thread.sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
