package org.masch.exercise.stock.factory;

import java.util.ArrayList;
import java.util.Collection;

import org.masch.exercise.stock.enums.StockActionMarketStrategyEnum;
import org.masch.exercise.stock.strategy.StockMarketStrategyService;
import org.masch.exercise.stock.company.service.StockExchangeService;
import org.masch.exercise.stock.validation.AbstractStockActionValidation;
import org.masch.exercise.stock.validation.buy.service.FirstBuyStockActionValidation;
import org.masch.exercise.stock.validation.buy.service.SecondBuyStockActionValidation;
import org.masch.exercise.stock.validation.sell.service.FirstSellStockActionValidation;
import org.masch.exercise.stock.validation.sell.service.SecondSellStockActionValidation;
import org.masch.exercise.stock.validation.sell.service.LastDayMonthSellStockActionValidation;

public class StockMarketStrategyFactory {

    private final static double MAX_INVERSION_AMOUNT = 1000;

    public StockMarketStrategyService getStockMarketStrategy(StockActionMarketStrategyEnum stockActionMarketStrategyEnum) {
        StockMarketStrategyService result = null;
        StockExchangeService stockExchangeService = new StockExchangeService();

        switch (stockActionMarketStrategyEnum) {
            case FIRST_STRATEGY:
                result = getFirstStockMarketStrategy(stockExchangeService);
                break;
            case SECOND_STRATEGY:
                result = getSecondStockMarketStrategy(stockExchangeService);
                break;
        }

        return result;
    }

    private StockMarketStrategyService getFirstStockMarketStrategy(StockExchangeService stockExchangeService) {

        Collection<AbstractStockActionValidation> buyStockActionValidationsList = new ArrayList<>();
        buyStockActionValidationsList.add(new FirstBuyStockActionValidation());

        Collection<AbstractStockActionValidation> sellStockActionValidationsList = new ArrayList<>();
        sellStockActionValidationsList.add(new FirstSellStockActionValidation());
        sellStockActionValidationsList.add(new LastDayMonthSellStockActionValidation());

        return new StockMarketStrategyService(buyStockActionValidationsList, sellStockActionValidationsList, MAX_INVERSION_AMOUNT, stockExchangeService, StockActionMarketStrategyEnum.FIRST_STRATEGY);
    }

    private StockMarketStrategyService getSecondStockMarketStrategy(StockExchangeService stockExchangeService) {

        Collection<AbstractStockActionValidation> buyStockActionValidationsList = new ArrayList<>();
        buyStockActionValidationsList.add(new FirstBuyStockActionValidation());
        buyStockActionValidationsList.add(new SecondBuyStockActionValidation());

        Collection<AbstractStockActionValidation> sellStockActionValidationsList = new ArrayList<>();
        sellStockActionValidationsList.add(new SecondSellStockActionValidation());
        sellStockActionValidationsList.add(new LastDayMonthSellStockActionValidation());

        return new StockMarketStrategyService(buyStockActionValidationsList, sellStockActionValidationsList, MAX_INVERSION_AMOUNT, stockExchangeService, StockActionMarketStrategyEnum.SECOND_STRATEGY);
    }

}
