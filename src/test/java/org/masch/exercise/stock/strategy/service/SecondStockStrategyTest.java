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

public class SecondStockStrategyTest extends StockDataTest {

    private StockMarketStrategyService stockMarketStrategyService;

    @Before
    public void init() {
        super.init();

        StockMarketStrategyFactory stockMarketStrategyFactory = new StockMarketStrategyFactory();
        this.stockMarketStrategyService = stockMarketStrategyFactory.getStockMarketStrategy(StockActionMarketStrategyEnum.SECOND_STRATEGY);
        Assert.assertEquals(StockActionMarketStrategyEnum.SECOND_STRATEGY, this.stockMarketStrategyService.getStockActionMarketStrategyEnum());
    }

    @Test
    public void noStockValidationApplyThenSameInitialMoney() {

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 290);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 294);

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(3, 500,"1/11/2016");

        //Variation: 1.3699 - 3 previous stock actions - No stock validation apply (nothing to do, same initial money)
        UserAcquisitionDTO userAcquisitionDTOResult = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                userAcquisitionDTO, this.getDefaultCompanyActionMarketDTO());

        assertUserAcquisitionEquals(3, 500, userAcquisitionDTOResult);
    }

    @Test
    public void noPreviousStockActionSellStockValidationApplyThenSameInitialMoney() {

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 290);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("1/11/2016"), 299);

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(0, 500,"1/11/2016");

        //Variation: 3.10 - No previous stock actions - Second sell stock validation apply (nothing to sell, same initial money)
        UserAcquisitionDTO userAcquisitionDTOResult = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                userAcquisitionDTO, this.getDefaultCompanyActionMarketDTO());

        assertUserAcquisitionEquals(0, 500, userAcquisitionDTOResult);

    }

    @Test
    public void withPreviousStockActionWithMoreThan5DaysOldSecondSellStockValidationApplyThenIncreaseMoney() {

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(newDate("10/11/2016"), 257);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("11/11/2016"), 258);

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(5, 500,"1/11/2016");

        //Variation: 9 days old action - 5 previous  stock actions - Second sell validation apply (258×5 + 500)
        UserAcquisitionDTO userAcquisitionDTOResult = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                userAcquisitionDTO, this.getDefaultCompanyActionMarketDTO());

        assertUserAcquisitionEquals(0, 1790, userAcquisitionDTOResult);

    }

    @Test
    public void withPreviousStockActionWithLessDoubleAverageSellStockValidationApplyThenIncreaseMoney() {

        CompanyStockActionDTO company = this.getGGALCompany();
        List<StockActionDTO> stocksActionsList = company.getStocksActionsList();
        double averageValue = this.getStockExchangeService().getAverageValue(this.newDate("10/11/2016"), stocksActionsList); //297

        StockActionDTO oldStockActionDTO = this.createStockActionDTO(newDate("06/11/2016"), averageValue);
        StockActionDTO newStockActionDTO = this.createStockActionDTO(newDate("11/11/2016"), 595);

        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(5, 1500,"1/11/2016");

        //Variation: 5 previous  stock actions - Second sell validation apply (1500 + 595 * 5)
        UserAcquisitionDTO userAcquisitionDTOResult = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                userAcquisitionDTO, company.getStockActionMarketDTO());

        assertUserAcquisitionEquals(0, 4475, userAcquisitionDTOResult);

    }

    @Test
    public void withPreviousStockActionWithLessDoubleAverageHistorySellStockValidationApplyThenIncreaseMoney() {

        CompanyStockActionDTO company = this.getGGALCompany();
        List<StockActionDTO> stocksActionsList = company.getStocksActionsList();
        UserAcquisitionDTO userAcquisitionDTO = createUserAcquisitionDTO(0, 1500,"1/1/2016");

        for (StockActionDTO stockActionDTO : stocksActionsList) {
            double averageValue = this.getStockExchangeService().getAverageValue(stockActionDTO.getDate(), stocksActionsList);
            StockActionDTO oldStockActionDTO = this.createStockActionDTO(stockActionDTO.getDate(), averageValue);
            StockActionDTO newStockActionDTO = this.createStockActionDTO(stockActionDTO.getDate(), stockActionDTO.getValue());

            //Variation: 5 previous  stock actions - History actions
            userAcquisitionDTO = this.stockMarketStrategyService.execute(oldStockActionDTO, newStockActionDTO,
                    userAcquisitionDTO, company.getStockActionMarketDTO());

        }
        assertUserAcquisitionEquals(2, 106.37, userAcquisitionDTO);

    }

    @Test
    public void test8() {

        CompanyStockActionDTO company = this.getYPFCompany();
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
        assertUserAcquisitionEquals(0,6536, userAcquisitionDTOInventoryList.get(7)); // Sell (After 5 days bought)
        assertUserAcquisitionEquals(0,6536, userAcquisitionDTOInventoryList.get(8)); // Nothing
        assertUserAcquisitionEquals(3,5606, userAcquisitionDTOInventoryList.get(9)); // Buy

    }

}
