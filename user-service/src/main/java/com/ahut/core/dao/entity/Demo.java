package com.ahut.core.dao.entity;

public class Demo {

    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String ID = "id";

    private String name;

    private String id;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}