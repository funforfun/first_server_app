package com.company;

abstract class MessageToAccountService extends Message {

    MessageToAccountService(Address from, Address to) {
        super(from, to);
    }

    @Override
    void exec(Abonent abonent) throws RuntimeException {
        if (abonent instanceof AccountService) {
            exec((AccountService) abonent);
        } else {
            throw new RuntimeException("Wrong type of abonent!" + abonent.getClass());
        }
    }

    abstract void exec(AccountService accountService);
}
