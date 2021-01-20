package com.xugw.kettlecore.entity;

import com.xugw.kettlecore.util.GetCurrentDate;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Title: 基础实体类
 * @Description:
 * @Author: xugw
 * @Date: 2021/1/12 - 15:31
 */
public class BaseEntity {
    private Long id;
    private Timestamp create_at;
    private Timestamp update_at;

    public BaseEntity() {
        Timestamp timestamp = GetCurrentDate.getCurrentsqlDate();
        this.create_at = timestamp;
        this.update_at = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id='" + id + '\'' +
                ", create_at=" + create_at +
                ", update_at=" + update_at +
                '}';
    }

}
