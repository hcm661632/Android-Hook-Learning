package com.hua.hookbinder;

import com.hua.hookbinder.reflect.Man;
import com.hua.hookbinder.reflect.People;
import com.hua.hookbinder.reflect.Woman;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testReflect(){

        testPerson();


    }

    void testPerson(){
        try {
            Class<?> peopleCls = Class.forName("com.hua.hookbinder.reflect.People");
            People people = (People) peopleCls.newInstance();
            Field mManField = peopleCls.getDeclaredField("mMan");      mManField.setAccessible(true);

            Man m = (Man) mManField.get(people);
            System.out.println(m.getName());

            Field mWomanField = peopleCls.getDeclaredField("mWoman"); mWomanField.setAccessible(true);
            Woman woman = (Woman) mWomanField.get(null);
            System.out.println(woman.getClass().getName());

            Class<?> womanCls = Class.forName("com.hua.hookbinder.reflect.Woman");
            Field womanNameFiled = womanCls.getDeclaredField("name"); womanNameFiled.setAccessible(true);
            Object o = womanNameFiled.get(woman);
            System.out.println(o);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }  catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


}