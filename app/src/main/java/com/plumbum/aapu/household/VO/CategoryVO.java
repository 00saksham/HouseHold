package com.plumbum.aapu.household.VO;

import java.sql.Blob;

/**
 * Created by Dawn on 6/18/2016.
 */
public class CategoryVO
{
    String category_name;
    Blob category_icon;
    int id;

    public Blob getCategory_icon() {
        return category_icon;
    }

    public void setCategory_icon(Blob category_icon) {
        this.category_icon = category_icon;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
