package com.epam.edu.memory.management;

public class Main {

    public static void passObjectByReference(MyObject obj) {
        obj.setId(100);
        obj.setName("New Name");
    }

    public static void passObjectByReferenceWithNewObject(MyObject obj) {
        obj = new MyObject(999, "Sample");
        System.out.println(obj);
    }

    public static void main(String[] args) {
        MyObject object = new MyObject();
        System.out.println(object);
        passObjectByReference(object);
        System.out.println(object);
        passObjectByReferenceWithNewObject(object);
        System.out.println(object);
        while(true) {        }
    }

}
