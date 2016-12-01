package org.masch.exercise.stock.strategy;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;

import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.UserAcquisitionDTO;
import org.masch.exercise.stock.dto.StockActionMarketDTO;
import org.masch.exercise.stock.enums.StockActionMarketStrategyEnum;
import org.masch.exercise.stock.company.service.StockExchangeService;
import org.masch.exercise.stock.validation.AbstractStockActionValidation;

public class StockMarketStrategyService {

    private double maxInversionAmount;
    private StockExchangeService stockExchangeService;
    private Collection<AbstractStockActionValidation> buyStrategyList;
    private Collection<AbstractStockActionValidation> sellStrategyList;
    private StockActionMarketStrategyEnum stockActionMarketStrategyEnum;

    public StockMarketStrategyService(Collection<AbstractStockActionValidation> buyStrategyList, Collection<AbstractStockActionValidation> sellStrategyList, double maxInversionAmount, StockExchangeService stockExchangeService, StockActionMarketStrategyEnum stockActionMarketStrategyEnum) {
        this.buyStrategyList = buyStrategyList;
        this.sellStrategyList = sellStrategyList;
        this.maxInversionAmount = maxInversionAmount;
        this.stockExchangeService = stockExchangeService;
        this.stockActionMarketStrategyEnum = stockActionMarketStrategyEnum;
    }

    public UserAcquisitionDTO execute(StockActionDTO oldStocksActionsDTO, StockActionDTO newStocksActionsDTO, UserAcquisitionDTO userAcquisitionDTO, StockActionMarketDTO companyStockAction) {

        UserAcquisitionDTO result;
        double userMoney = userAcquisitionDTO.getMoney();
        double newStockActionValue = newStocksActionsDTO.getValue();

        if ((userAcquisitionDTO.getStockActionMarketDTO().getCount() > 0)
                && (checkStockActionValidationStrategies(oldStocksActionsDTO, newStocksActionsDTO, userAcquisitionDTO, this.sellStrategyList))) {

            result = sellAllStockAction(userAcquisitionDTO, newStockActionValue);

        } else if ((userMoney >= newStockActionValue)
                && (checkStockActionValidationStrategies(oldStocksActionsDTO, newStocksActionsDTO, userAcquisitionDTO, this.buyStrategyList))) {

            result = buyAllActionsPossible(this.maxInversionAmount, userAcquisitionDTO, newStocksActionsDTO, companyStockAction.getCount());

        } else {
            result = userAcquisitionDTO;
        }

        return result;
    }

    private boolean checkStockActionValidationStrategies(StockActionDTO oldStocksActionsDTO, StockActionDTO newStocksActionsDTO, UserAcquisitionDTO userAcquisitionDTO, Collection<AbstractStockActionValidation> stockValidationsList) {
        boolean result = false;
        AbstractStockActionValidation stockActionValidation;
        Iterator<AbstractStockActionValidation> iterator = stockValidationsList.iterator();

        while ((!result) && (iterator.hasNext())) {
            stockActionValidation = iterator.next();
            result = stockActionValidation.check(oldStocksActionsDTO, newStocksActionsDTO, userAcquisitionDTO);
        }

        return result;
    }

    private UserAcquisitionDTO sellAllStockAction(UserAcquisitionDTO userAcquisitionDTO, double stockValue) {
        UserAcquisitionDTO result = new UserAcquisitionDTO();

        StockActionMarketDTO stockActionMarketDTO = new StockActionMarketDTO();
        stockActionMarketDTO.setCount(0);

        result.setStockActionMarketDTO(stockActionMarketDTO);
        result.setMoney(userAcquisitionDTO.getMoney() + userAcquisitionDTO.getStockActionMarketDTO().getCount() * stockValue);

        return result;
    }

    private UserAcquisitionDTO buyAllActionsPossible(double maxInversionAmount, UserAcquisitionDTO userAcquisitionDTO, StockActionDTO newStocksActionsDTO, int maxActionCount) {
        UserAcquisitionDTO result = new UserAcquisitionDTO();

        double stockActionValue = newStocksActionsDTO.getValue();
        double inversionAmount = getInversionAmount(maxInversionAmount, userAcquisitionDTO.getMoney());

        StockActionMarketDTO stockActionMarketDTO = this.stockExchangeService.buyStockActions(inversionAmount, stockActionValue, newStocksActionsDTO.getDate(), maxActionCount);
        result.setMoney(userAcquisitionDTO.getMoney() - (stockActionValue * stockActionMarketDTO.getCount()));
        stockActionMarketDTO.setCount(stockActionMarketDTO.getCount() + userAcquisitionDTO.getStockActionMarketDTO().getCount());
        result.setStockActionMarketDTO(stockActionMarketDTO);

        return result;
    }

    private double getInversionAmount(double maxInversionAmount, double userMoney) {
        double inversionAmount;

        if (userMoney > maxInversionAmount) {
            inversionAmount = maxInversionAmount;
        } else {
            inversionAmount = userMoney;
        }

        return inversionAmount;
    }

    public List<UserAcquisitionDTO> execute(List<StockActionDTO> stocksActionsList, UserAcquisitionDTO userAcquisitionDTO, StockActionMarketDTO stockActionMarketDTO) {
        List<UserAcquisitionDTO> result = new ArrayList<>();

        if (!stocksActionsList.isEmpty()) {
            StockActionDTO nextStockAction;
            UserAcquisitionDTO userAcquisitionDTOResult = userAcquisitionDTO;
            Iterator<StockActionDTO> stockActionDTOIterator = stocksActionsList.iterator();
            StockActionDTO previousStockAction = stockActionDTOIterator.next();

            while (stockActionDTOIterator.hasNext()) {
                nextStockAction = stockActionDTOIterator.next();

                userAcquisitionDTOResult = this.execute(previousStockAction, nextStockAction, userAcquisitionDTOResult, stockActionMarketDTO);
                result.add(userAcquisitionDTOResult);

                previousStockAction = nextStockAction;
            }

        }

        return result;
    }

    public StockActionMarketStrategyEnum getStockActionMarketStrategyEnum() {
        return stockActionMarketStrategyEnum;
    }

}
