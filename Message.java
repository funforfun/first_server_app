package com.company;

abstract class Message {
    public abstract Address getTo();

    public abstract void exec();
}
