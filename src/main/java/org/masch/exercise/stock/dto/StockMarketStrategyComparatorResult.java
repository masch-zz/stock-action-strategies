package org.masch.exercise.stock.dto;

import java.util.List;

import org.masch.exercise.stock.enums.StockActionMarketStrategyEnum;

public class StockMarketStrategyComparatorResult {

    private double finalMoney;
    private double initialMoney;
    private List<UserAcquisitionDTO> userAcquisitionDTOList;
    private StockActionMarketStrategyEnum stockActionMarketStrategyEnum;

    public StockMarketStrategyComparatorResult(double initialMoney, double finalMoney, List<UserAcquisitionDTO> userAcquisitionDTOList, StockActionMarketStrategyEnum stockActionMarketStrategyEnum) {
        this.finalMoney = finalMoney;
        this.initialMoney = initialMoney;
        this.userAcquisitionDTOList = userAcquisitionDTOList;
        this.stockActionMarketStrategyEnum = stockActionMarketStrategyEnum;
    }

    public List<UserAcquisitionDTO> getUserAcquisitionDTOList() {
        return userAcquisitionDTOList;
    }

    public StockActionMarketStrategyEnum getStockActionMarketStrategyEnum() {
        return stockActionMarketStrategyEnum;
    }

    public double getInitialMoney() {
        return initialMoney;
    }

    public double getFinalMoney() {
        return finalMoney;
    }
}
