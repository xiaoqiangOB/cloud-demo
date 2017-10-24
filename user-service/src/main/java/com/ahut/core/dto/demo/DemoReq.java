package com.ahut.core.dto.demo;

import java.io.Serializable;

/**
 * Created by c2292 on 2017/10/23.
 */
public class DemoReq implements Serializable{
    private static final long serialVersionUID = 4852649831903869288L;
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
