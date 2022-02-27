package org.example.ioc.domain;

import org.example.ioc.annotation.Super;

/**
 * @description: 超级客户
 * @author: hitton
 * @create: 2021-03-03 23:58
 **/
@Super
public class SuperUser extends User{
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
