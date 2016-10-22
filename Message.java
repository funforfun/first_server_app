package com.company;

abstract class Message {
    abstract Address getTo();

    abstract void exec();
}
