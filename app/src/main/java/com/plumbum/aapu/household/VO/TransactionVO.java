package com.plumbum.aapu.household.VO;

import java.sql.Blob;
import java.sql.Date;

/**
 * Created by Dawn on 6/18/2016.
 */
public class TransactionVO
{
    Float sum;
    Date date;
    String remarks;
    Blob category_icon;
    String category_name;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }
}
