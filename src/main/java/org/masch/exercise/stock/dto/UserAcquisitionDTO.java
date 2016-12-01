package org.masch.exercise.stock.dto;

public class UserAcquisitionDTO {

    private double money;
    private StockActionMarketDTO stockActionMarketDTO;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public StockActionMarketDTO getStockActionMarketDTO() {
        return stockActionMarketDTO;
    }

    public void setStockActionMarketDTO(StockActionMarketDTO stockActionMarketDTO) {
        this.stockActionMarketDTO = stockActionMarketDTO;
    }

}
