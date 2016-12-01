package org.masch.exercise.stock.validation.buy.service;

import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.UserAcquisitionDTO;
import org.masch.exercise.stock.enums.StockActionValidationEnum;
import org.masch.exercise.stock.validation.AbstractPercentageDifferenceValueStockActionValidation;

public class SecondBuyStockActionValidation extends AbstractPercentageDifferenceValueStockActionValidation {

    public SecondBuyStockActionValidation() {
        super(StockActionValidationEnum.SECOND_BUY);
    }

    @Override
    public boolean check(StockActionDTO oldStocksActionsDTO, StockActionDTO newStocksActionsDTO, UserAcquisitionDTO userAcquisitionDTO) {
        return (this.calculatePercentageDifference(oldStocksActionsDTO.getValue(), newStocksActionsDTO.getValue()) < -50);
    }
}
