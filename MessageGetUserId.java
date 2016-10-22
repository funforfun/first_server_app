package com.company;

class MessageGetUserId extends MessageToAccountService{
    private String name;


    MessageGetUserId(Address from, Address to, String name) {
        super(from, to);
        this.name = name;
    }

    void exec(AccountService accountService) {
        Integer id = accountService.getUserId(name);
        accountService.getMessageSystem().sendMessage(new MessageUpdateUserId(getTo(),getFrom(), name, id));
    }
}
