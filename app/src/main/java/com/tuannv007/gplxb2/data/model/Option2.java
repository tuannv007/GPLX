package com.tuannv007.gplxb2.data.model;

public class Option2 {
    private String option2;
    private int id;
    private boolean isSelect;

    public Option2(String option2, int id) {
        this.option2 = option2;
        this.id = id;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
