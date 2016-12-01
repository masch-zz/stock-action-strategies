package org.masch.exercise.stock.validation.sell.service;

import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.UserAcquisitionDTO;
import org.masch.exercise.stock.enums.StockActionValidationEnum;
import org.masch.exercise.stock.validation.AbstractPercentageDifferenceValueStockActionValidation;

public class FirstSellStockActionValidation extends AbstractPercentageDifferenceValueStockActionValidation {

    public FirstSellStockActionValidation(){
        super(StockActionValidationEnum.FIRST_SELL);
    }

    @Override
    public boolean check(StockActionDTO oldStocksActionsDTO, StockActionDTO newStocksActionsDTO, UserAcquisitionDTO userAcquisitionDTO) {
        return (this.calculatePercentageDifference(oldStocksActionsDTO.getValue(), newStocksActionsDTO.getValue()) > 2);
    }

}
