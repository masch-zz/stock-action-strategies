package org.masch.exercise.stock.strategy.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static junit.framework.TestCase.assertFalse;

import org.masch.exercise.stock.StockDataTest;
import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.UserAcquisitionDTO;
import org.masch.exercise.stock.dto.CompanyStockActionDTO;
import org.masch.exercise.stock.factory.StockMarketStrategyFactory;
import org.masch.exercise.stock.enums.StockActionMarketStrategyEnum;
import org.masch.exercise.stock.strategy.StockMarketStrategyService;

public class FirstStockStrategyTest extends StockDataTest {

    private StockMarketStrategyService stockMarketStrategyService;

    @Before
    public void init() {
        super.init();

        StockMarketStrategyFactory stockMarketStrategyFactory = new StockMarketStrategyFactory();
        this.stockMarketStrategyService = stockMarketStrategyFactory.getStockMarketStrategy(StockActionMarketStrategyEnum.FIRST_STRATEGY);
        Assert.assertEquals(StockActionMarketStrategyEnum.FIRST_STRATEGY, this.stockMarketStrategyService.getStockActionMarketStrategyEnum());
    }

    @Test
    public void noStockValidationApplyThenSameInitialMoney() {

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 290);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 294);

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(3, 500, "1/11/2016");

        //Variation: 1.3699 - 3 previous stock actions - No stock validation apply (nothing to do, same initial money)
        UserAcquisitionDTO userAcquisitionDTOResult = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                userAcquisitionDTO, this.getDefaultCompanyActionMarketDTO());

        assertUserAcquisitionEquals(3,500, userAcquisitionDTOResult);

    }

    @Test
    public void noPreviousStockActionSellStockValidationApplyThenSameInitialMoney() {

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 290);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 299);

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(0, 500,"1/11/2016");

        //Variation: 3.10 - No previous stock actions - First sell stock validation apply (nothing to sell, same initial money)
        UserAcquisitionDTO userAcquisitionDTOResult = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                userAcquisitionDTO, this.getDefaultCompanyActionMarketDTO());

        assertUserAcquisitionEquals(0,500, userAcquisitionDTOResult);

    }

    @Test
    public void noPreviousStockActionSellStockValidationApplyThenIncreaseMoney() {

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 263);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 258);

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(0, 500,"1/11/2016");

        //Variation: -1.90 - No previous stock actions - First buy stock validation apply (500 - 258)
        UserAcquisitionDTO userAcquisitionDTOResult = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                userAcquisitionDTO, this.getDefaultCompanyActionMarketDTO());

        assertUserAcquisitionEquals(1,242, userAcquisitionDTOResult);

    }

    @Test
    public void withPreviousStockActionBuyStockValidationApplyThenIncreaseMoney() {

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 263);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 258);

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(5, 500,"1/11/2016");

        //Variation: -1.90 - 5 stock actions - First buy stock validation apply (500 - 258)
        UserAcquisitionDTO userAcquisitionDTOResult = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                userAcquisitionDTO, this.getDefaultCompanyActionMarketDTO());

        assertUserAcquisitionEquals(6,242, userAcquisitionDTOResult);

    }

    @Test
    public void withPreviousStockActionWithMoreInitialValueThanMaxInversionBuyStockValidationApplyThenIncreaseMoney() {

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 263);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 258);

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(5, 5000,"1/11/2016");

        //Variation: -1.90 - 5 stock actions - First buy stock validation apply (5000 - 258 - 258 - 258)
        UserAcquisitionDTO userAcquisitionDTOResult = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                userAcquisitionDTO, this.getDefaultCompanyActionMarketDTO());

        assertUserAcquisitionEquals(8,4226, userAcquisitionDTOResult);
    }

    @Test
    public void withPreviousStockActionSellStockValidationApplyThenIncreaseMoney() {

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 263);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 270);

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(5, 500,"1/11/2016");

        //Variation: 2.66 - 5 stock actions - First sell stock validation apply (500 + (270 * 5))
        UserAcquisitionDTO userAcquisitionDTOResult = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                userAcquisitionDTO, this.getDefaultCompanyActionMarketDTO());

        assertUserAcquisitionEquals(0,1850, userAcquisitionDTOResult);
    }

    @Test
    public void withPreviousStockActionLastDayOfMonthSellStockValidationApplyThenIncreaseMoney() {

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(newDate("29/11/2016"), 263);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("30/11/2016"), 258);

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(5, 5000,"1/11/2016");

        //Variation: 3.06 - 5 stock actions - Last day of the month validation sell apply (5000 - 258*5)
        UserAcquisitionDTO userAcquisitionDTOResult = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                userAcquisitionDTO, this.getDefaultCompanyActionMarketDTO());

        assertUserAcquisitionEquals(0,6290, userAcquisitionDTOResult);
    }

    @Test
    public void stockActionHistoryMixStockValidationThenUserAcquisitionResult() {

        CompanyStockActionDTO company = getYPFCompany();
        List<StockActionDTO> stocksActionsList = company.getStocksActionsList();

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(5, 5000,"1/11/2016");

        //Variation: 3.06 - 5 stock actions - History actions
        List<UserAcquisitionDTO> userAcquisitionDTOInventoryList = this.stockMarketStrategyService.execute(stocksActionsList,
                userAcquisitionDTO, company.getStockActionMarketDTO());

        assertNotNull(userAcquisitionDTOInventoryList);
        assertFalse(userAcquisitionDTOInventoryList.isEmpty());
        assertEquals(10, userAcquisitionDTOInventoryList.size());

        assertUserAcquisitionEquals(5,5000, userAcquisitionDTOInventoryList.get(0)); // Nothing
        assertUserAcquisitionEquals(8,4136, userAcquisitionDTOInventoryList.get(1)); // Buy
        assertUserAcquisitionEquals(8,4136, userAcquisitionDTOInventoryList.get(2)); // Nothing
        assertUserAcquisitionEquals(8,4136, userAcquisitionDTOInventoryList.get(3)); // Nothing
        assertUserAcquisitionEquals(8,4136, userAcquisitionDTOInventoryList.get(4)); // Nothing
        assertUserAcquisitionEquals(8,4136, userAcquisitionDTOInventoryList.get(5)); // Nothing
        assertUserAcquisitionEquals(8,4136, userAcquisitionDTOInventoryList.get(6)); // Nothing
        assertUserAcquisitionEquals(8,4136, userAcquisitionDTOInventoryList.get(7)); // Nothing
        assertUserAcquisitionEquals(0,6696, userAcquisitionDTOInventoryList.get(8)); // Sell
        assertUserAcquisitionEquals(3,5766, userAcquisitionDTOInventoryList.get(9)); // Buy

    }

}

