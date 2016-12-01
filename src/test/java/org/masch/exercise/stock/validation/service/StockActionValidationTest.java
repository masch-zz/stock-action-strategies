package org.masch.exercise.stock.validation.service;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

import org.masch.exercise.stock.StockDataTest;
import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.UserAcquisitionDTO;
import org.masch.exercise.stock.enums.StockActionValidationEnum;
import org.masch.exercise.stock.validation.buy.service.FirstBuyStockActionValidation;
import org.masch.exercise.stock.validation.buy.service.SecondBuyStockActionValidation;
import org.masch.exercise.stock.validation.sell.service.FirstSellStockActionValidation;
import org.masch.exercise.stock.validation.sell.service.SecondSellStockActionValidation;
import org.masch.exercise.stock.validation.sell.service.LastDayMonthSellStockActionValidation;

public class StockActionValidationTest extends StockDataTest {

    @Test
    public void firstBuyStrategyComply() {

        FirstBuyStockActionValidation firstBuyStrategy = new FirstBuyStockActionValidation();

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(null, 294);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(null, 288);

        assertTrue(firstBuyStrategy.check(oldStockActionDTO, newStockActionDTO, null)); // -2.04%

    }

    @Test
    public void firstBuyStrategyBecauseIncreasePercentageNOTComply() {

        FirstBuyStockActionValidation firstBuyStrategy = new FirstBuyStockActionValidation();

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(null, 290);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(null, 294);

        assertEquals(firstBuyStrategy.getStockActionValidationEnum().getName(), StockActionValidationEnum.FIRST_BUY.getName());
        assertFalse(firstBuyStrategy.check(oldStockActionDTO, newStockActionDTO, null)); // 1.37%

    }

    @Test
    public void firstBuyStrategyBecauseDecreasePercentageLessThanTopNOTComply() {

        FirstBuyStockActionValidation firstBuyStrategy = new FirstBuyStockActionValidation();

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(null, 289);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(null, 290);

        assertFalse(firstBuyStrategy.check(oldStockActionDTO, newStockActionDTO, null)); // -0.344%
    }

    @Test
    public void firstSellStrategyComply() {

        FirstSellStockActionValidation firstSellStrategy = new FirstSellStockActionValidation();

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(null, 290);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(null, 300);

        assertTrue(firstSellStrategy.check(oldStockActionDTO, newStockActionDTO, null)); // 3.44%

    }

    @Test
    public void firstSellStrategyBecauseIncreaseLessThanUpperTopNOTComply() {

        FirstSellStockActionValidation firstSellStrategy = new FirstSellStockActionValidation();

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(null, 295);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(null, 290);

        assertFalse(firstSellStrategy.check(newStockActionDTO, oldStockActionDTO, null)); // 1.72%

    }

    @Test
    public void secondBuyStrategyComply() {

        SecondBuyStockActionValidation secondBuyStrategy = new SecondBuyStockActionValidation();

        List<StockActionDTO> stocksActionsList = this.getYPFCompany().getStocksActionsList();
        double newValue = this.getStockExchangeService().getAverageValue(this.newDate("06/11/2016"), stocksActionsList);
        StockActionDTO oldStockActionDTO = this.createStockActionDTO(null, 600);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(null, newValue);

        assertTrue(secondBuyStrategy.check(oldStockActionDTO, newStockActionDTO, null)); // -51%

    }

    @Test
    public void secondBuyStrategyBecauseIncreaseLessThanTopNOTComply() {

        SecondBuyStockActionValidation secondBuyStrategy = new SecondBuyStockActionValidation();

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(null, 575);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(null, 290);

        assertFalse(secondBuyStrategy.check(oldStockActionDTO, newStockActionDTO, null)); // 98%

    }

    @Test
    public void secondSellStrategyComply() {

        SecondSellStockActionValidation secondSellStrategy = new SecondSellStockActionValidation();

        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("16/01/2016"), 0);
        UserAcquisitionDTO userAcquisitionDTO = this.createUserAcquisitionDTO(0, 500, "10/01/2016");

        assertTrue(secondSellStrategy.check(null, newStockActionDTO, userAcquisitionDTO));

    }

    @Test
    public void secondSellStrategyNOTComply() {

        SecondSellStockActionValidation secondSellStrategy = new SecondSellStockActionValidation();

        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("16/01/2016"), 0);
        UserAcquisitionDTO userAcquisitionDTO = this.createUserAcquisitionDTO(0, 500, "13/01/2016");

        assertFalse(secondSellStrategy.check(null, newStockActionDTO, userAcquisitionDTO));

    }

    @Test
    public void lastDayMonthSellStrategyComply() {

        LastDayMonthSellStockActionValidation lastDayMonthSellStockActionValidation = new LastDayMonthSellStockActionValidation();

        StockActionDTO newStocksActionsDTO = this.createStockActionDTO(newDate("30/11/2016"), 0);

        assertTrue(lastDayMonthSellStockActionValidation.check(null, newStocksActionsDTO, null));

    }

    @Test
    public void lastDayMonthSellStrategyNOTComply() {

        LastDayMonthSellStockActionValidation lastDayMonthSellStockActionValidation = new LastDayMonthSellStockActionValidation();

        StockActionDTO newStocksActionsDTO = new StockActionDTO();
        newStocksActionsDTO.setDate(newDate("29/11/2016"));

        assertFalse(lastDayMonthSellStockActionValidation.check(null, newStocksActionsDTO, null));

    }

}
