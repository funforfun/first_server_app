package com.company;

interface MessageSystem {
    void addService(Abonent abonent);

    void sendMessage(Message message);

    void execForAbonent(Abonent abonent);

    AddressService getAddressService();
}
