package com.company;

abstract class Message {
    private Address from;
    private Address to;

    protected Message(Address from, Address to) {
        this.from = from;
        this.to = to;
    }

    Address getFrom() {
        return from;
    }

    Address getTo() {
        return to;
    }

    abstract void exec(Abonent abonent);
}
