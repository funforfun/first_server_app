package com.company;

import java.util.HashMap;
import java.util.Map;

class AddressServiceImpl implements AddressService {
    private Map<Class<?>, Address> addresses = new HashMap<Class<?>, Address>();

    public Address getAddress(Class<?> abonentClass) {
        return addresses.get(abonentClass);
    }

    public void setAddress(Abonent abonent) {
        addresses.put(abonent.getClass(), abonent.getAddress());
    }
}
