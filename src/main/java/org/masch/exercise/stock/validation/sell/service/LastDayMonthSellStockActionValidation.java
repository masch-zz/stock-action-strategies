package org.masch.exercise.stock.validation.sell.service;

import org.masch.exercise.stock.util.DateUtils;
import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.UserAcquisitionDTO;
import org.masch.exercise.stock.enums.StockActionValidationEnum;
import org.masch.exercise.stock.validation.AbstractPercentageDifferenceValueStockActionValidation;

public class LastDayMonthSellStockActionValidation extends AbstractPercentageDifferenceValueStockActionValidation {

    public LastDayMonthSellStockActionValidation(){
        super(StockActionValidationEnum.LAST_DAY_MONTH_SELL);
    }

    @Override
    public boolean check(StockActionDTO oldStocksActionsDTO, StockActionDTO newStocksActionsDTO, UserAcquisitionDTO userAcquisitionDTO) {
        boolean a = DateUtils.isLastDayOfTheMonth(newStocksActionsDTO.getDate());
        return DateUtils.isLastDayOfTheMonth(newStocksActionsDTO.getDate());
    }

}
