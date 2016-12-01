package org.masch.exercise.stock.dto;

import java.util.Date;

public class StockActionDTO {

    private Date date;
    private double value;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
