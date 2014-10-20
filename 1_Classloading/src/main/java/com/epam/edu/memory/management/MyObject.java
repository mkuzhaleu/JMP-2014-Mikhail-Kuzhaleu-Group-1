package com.epam.edu.memory.management;

public class MyObject {

    private int id;
    private String name;

    public MyObject(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public MyObject() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "My object is [id=" + id + ", name= " + name + "]";
    }

}
