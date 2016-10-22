package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class MessageSystem {

    private Map<Address, ConcurrentLinkedQueue<Message>> messages = new HashMap<Address, ConcurrentLinkedQueue<Message>>();
    private AddressService addressService = new AddressService();

    void addService(Abonent abonent) {
        addressService.setAddress(abonent);
        messages.put(abonent.getAddress(), new ConcurrentLinkedQueue<Message>());
    }

    void sendMessage(Message message) {
        Queue<Message> messageQueue = messages.get(message.getTo());
        messageQueue.add(message);
    }

    void execForAbonent(Abonent abonent) {
        Queue<Message> messageQueue = messages.get(abonent.getAddress());

        if (messageQueue == null) {
            return;
        }

        while (!messageQueue.isEmpty()) {
            Message message = messageQueue.poll();
            message.exec();
        }
    }

    AddressService getAddressService(){
        return addressService;
    }
}
