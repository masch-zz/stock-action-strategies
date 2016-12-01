package org.masch.exercise.stock.validation.sell.service;

import java.time.Duration;

import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.UserAcquisitionDTO;
import org.masch.exercise.stock.enums.StockActionValidationEnum;
import org.masch.exercise.stock.validation.AbstractStockActionValidation;

public class SecondSellStockActionValidation extends AbstractStockActionValidation {

    private final long DIFFERENCE_DAY = Duration.ofDays(5).toMillis();

    public SecondSellStockActionValidation() {
        super(StockActionValidationEnum.SECOND_SELL);
    }

    @Override
    public boolean check(StockActionDTO oldStocksActionsDTO, StockActionDTO newStocksActionsDTO, UserAcquisitionDTO userAcquisitionDTO) {

        long diff = (newStocksActionsDTO.getDate().getTime() - userAcquisitionDTO.getStockActionMarketDTO().getBuyDate().getTime());

        return diff > DIFFERENCE_DAY;
    }

}
