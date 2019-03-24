package com.tuannv007.gplxb2.data.model;

import androidx.databinding.BaseObservable;

public class BaseModel extends BaseObservable {
    private int zPk;
    private int zEnt;
    private int zOPT;

    public int getzPk() {
        return zPk;
    }

    public void setzPk(int zPk) {
        this.zPk = zPk;
    }

    public int getzEnt() {
        return zEnt;
    }

    public void setzEnt(int zEnt) {
        this.zEnt = zEnt;
    }

    public int getzOPT() {
        return zOPT;
    }

    public void setzOPT(int zOPT) {
        this.zOPT = zOPT;
    }
}
