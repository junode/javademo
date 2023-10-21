package org.example;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiPrac {
    public static void main(String[] args) {
        ServiceLoader<Pay> pays = ServiceLoader.load(Pay.class);
        Iterator<Pay> paysIter = pays.iterator();
        while(paysIter.hasNext()) {
            Pay next = paysIter.next();
            next.pay();
        }
    }
}
