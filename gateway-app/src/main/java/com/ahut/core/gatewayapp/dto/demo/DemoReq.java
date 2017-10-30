package com.ahut.core.gatewayapp.dto.demo;

import java.io.Serializable;

/**
 * Created by c2292 on 2017/10/25.
 */
public class DemoReq implements Serializable{
    private static final long serialVersionUID = 9019757323648073057L;
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
