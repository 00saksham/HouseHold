package com.plumbum.aapu.household.VO;

import java.sql.Blob;

/**
 * Created by Dawn on 6/18/2016.
 */
public class TransactionVO
{
    double sum;
    String date;
    String remarks;
    Blob category_icon;
    String category_name;
    String category_type;

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
