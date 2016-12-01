package org.masch.exercise.stock.strategy;

import java.util.List;

import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.UserAcquisitionDTO;
import org.masch.exercise.stock.dto.StockActionMarketDTO;
import org.masch.exercise.stock.dto.CompanyStockActionDTO;
import org.masch.exercise.stock.enums.StockActionMarketStrategyEnum;
import org.masch.exercise.stock.dto.StockMarketStrategyComparatorResult;

public class ComparatorStrategyService {

    public StockMarketStrategyComparatorResult compare(StockMarketStrategyService firstStockMarketStrategyService, StockMarketStrategyService secondStockMarketStrategyService, CompanyStockActionDTO company, UserAcquisitionDTO userAcquisitionDTO) {

        double initialMoney = userAcquisitionDTO.getMoney();
        List<StockActionDTO> stocksActionsList = company.getStocksActionsList();
        StockActionMarketDTO stockActionMarketDTO = company.getStockActionMarketDTO();

        List<UserAcquisitionDTO> userAcquisitionsFirstStrategyList = firstStockMarketStrategyService.execute(stocksActionsList, userAcquisitionDTO, stockActionMarketDTO);
        List<UserAcquisitionDTO> userAcquisitionsSecondStrategyList = secondStockMarketStrategyService.execute(stocksActionsList, userAcquisitionDTO, stockActionMarketDTO);

        UserAcquisitionDTO lastUserAcquisitionFirstStrategy = userAcquisitionsFirstStrategyList.get(userAcquisitionsFirstStrategyList.size() - 1);
        UserAcquisitionDTO lastUserAcquisitionSecondStrategy = userAcquisitionsSecondStrategyList.get(userAcquisitionsSecondStrategyList.size() - 1);

        StockMarketStrategyComparatorResult result;
        StockActionDTO lastStockActionDTO = stocksActionsList.get(stocksActionsList.size() - 1);
        double finalMoneyFirstStrategy = calculateFinalUserMoney(lastUserAcquisitionFirstStrategy, lastStockActionDTO.getValue());
        double finalMoneySecondStrategy = calculateFinalUserMoney(lastUserAcquisitionSecondStrategy, lastStockActionDTO.getValue());

        if (finalMoneyFirstStrategy > finalMoneySecondStrategy) {
            result = createComparatorResult(initialMoney, finalMoneyFirstStrategy, userAcquisitionsFirstStrategyList, firstStockMarketStrategyService.getStockActionMarketStrategyEnum());
        } else {
            result = createComparatorResult(initialMoney, finalMoneySecondStrategy, userAcquisitionsSecondStrategyList, secondStockMarketStrategyService.getStockActionMarketStrategyEnum());
        }

        return result;
    }

    private double calculateFinalUserMoney(UserAcquisitionDTO userAcquisitionDTO, double stockActionValue) {

        return userAcquisitionDTO.getMoney() + userAcquisitionDTO.getStockActionMarketDTO().getCount() * stockActionValue;

    }

    private StockMarketStrategyComparatorResult createComparatorResult(double initialMoney, double finalMoney, List<UserAcquisitionDTO> userAcquisitionsStrategyList, StockActionMarketStrategyEnum stockActionMarketStrategyEnum) {

        return new StockMarketStrategyComparatorResult(initialMoney, finalMoney, userAcquisitionsStrategyList, stockActionMarketStrategyEnum);
    }

}
