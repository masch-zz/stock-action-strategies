package org.masch.exercise.stock.dto;

import java.util.Date;

public class StockActionMarketDTO {

    private int count;
    private Date buyDate;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

}
