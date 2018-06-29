package com.hua.hookbinder.reflect;

public class People {

    Man man;

    public Man getMan() {
        if(man == null) {
            return new Man("MMan",12);
        } else {
            return man;
        }
    }
}
