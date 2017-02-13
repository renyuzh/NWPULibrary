package com.npu.library.web.bean;

import java.util.List;

public class PageBean<T> {
    private int total;

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
