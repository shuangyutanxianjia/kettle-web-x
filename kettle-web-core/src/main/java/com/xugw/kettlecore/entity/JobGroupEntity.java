package com.xugw.kettlecore.entity;

/**
 * @Title: jobgroup实体类
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/12 - 15:37
 */
public class JobGroupEntity {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "JobGroupEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
