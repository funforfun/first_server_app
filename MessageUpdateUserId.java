package com.company;

class MessageUpdateUserId extends MessageToFrontend{
    String name;
    Integer id;

    MessageUpdateUserId(Address from, Address to, String name, Integer id) {
        super(from, to);
        this.name = name;
        this.id = id;
    }

    void exec(Frontend frontend) {
        frontend.setId(name, id);
    }
}
