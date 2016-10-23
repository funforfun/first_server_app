package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class MessageSystemImpl implements MessageSystem {

    private Map<Address, ConcurrentLinkedQueue<Message>> messages = new HashMap<Address, ConcurrentLinkedQueue<Message>>();
    private AddressServiceImpl addressService = new AddressServiceImpl();

    public void addService(Abonent abonent) {
        addressService.setAddress(abonent);
        messages.put(abonent.getAddress(), new ConcurrentLinkedQueue<Message>());
    }

    public void sendMessage(Message message) {
        Queue<Message> messageQueue = messages.get(message.getTo());
        messageQueue.add(message);
    }

    public void execForAbonent(Abonent abonent) {
        Queue<Message> messageQueue = messages.get(abonent.getAddress());

        if (messageQueue == null) {
            return;
        }

        while (!messageQueue.isEmpty()) {
            Message message = messageQueue.poll();
            try {
                message.exec(abonent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public AddressService getAddressService(){
        return addressService;
    }
}
