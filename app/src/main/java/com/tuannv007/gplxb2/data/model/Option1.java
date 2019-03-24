package com.tuannv007.gplxb2.data.model;

public class Option1 {
    private String option1;
    private int id;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public Option1(String option1, int id) {
        this.option1 = option1;
        this.id = id;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
