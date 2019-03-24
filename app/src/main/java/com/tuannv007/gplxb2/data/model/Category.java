package com.tuannv007.gplxb2.data.model;

import androidx.room.Entity;

@Entity
public class Category extends BaseModel {
    private String zDescription;
    private int zId;
    private String name;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return zDescription;
    }

    public void setzDescription(String zDescription) {
        this.zDescription = zDescription;
    }

    public int getzId() {
        return zId;
    }

    public void setzId(int zId) {
        this.zId = zId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                ", zDescription='" + zDescription + '\'' +
                ", zId=" + zId +
                ", name='" + name + '\'' +
                '}';
    }
}
