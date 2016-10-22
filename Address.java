package com.company;

import java.util.concurrent.atomic.AtomicInteger;

class Address {
    static private AtomicInteger abonentIdCreator = new AtomicInteger();
    final private int abonentId;

    Address() {
        this.abonentId = abonentIdCreator.incrementAndGet();
    }

    public int hashCode(){
        return abonentId;
    }
}
