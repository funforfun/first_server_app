package com.company;

interface AccountService extends Abonent {
    Integer getUserId(String name);
    MessageSystem getMessageSystem();
}
