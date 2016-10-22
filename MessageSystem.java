package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class MessageSystem {

    private Map<Address, ConcurrentLinkedQueue<Message>> messages = new HashMap<Address, ConcurrentLinkedQueue<Message>>();

    public void sendMessage(Message message) {
        Queue<Message> messageQueue = messages.get(message.getTo());
    }

    public void execForAbonent(Abonent abonent) {
        Queue<Message> messageQueue = messages.get(abonent.getAddress());
        while (!messageQueue.isEmpty()) {
            Message message = messageQueue.poll();
            message.exec();
        }
    }
}
