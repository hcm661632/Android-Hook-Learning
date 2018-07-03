package com.hua.hookbinder.reflect;

public class People {

    public Man mMan = new Man("ManName",120);

    private static Woman mWoman = new Woman("staticWomanName",122);

    static String getWomanName(){

        if(mWoman == null ) {
            mWoman = new Woman("WomanName",111);
        }
        return mWoman.name;
    }


}
