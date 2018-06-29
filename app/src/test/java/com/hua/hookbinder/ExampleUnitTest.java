package com.hua.hookbinder;

import org.junit.Test;

import java.lang.reflect.Field;

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
            Class<?> personClz = Class.forName("com.hua.hookbinder.reflect.Person");
            Object o1 = personClz.newInstance();

            Field nameField = personClz.getDeclaredField("name");nameField.setAccessible(true);
           // nameField.set(o1,"Spring");
            Object o = nameField.get(o1);
            if(o != null) {
                System.out.println(o.toString());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}