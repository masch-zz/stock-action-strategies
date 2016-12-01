package org.masch.exercise.stock.strategy.service;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static junit.framework.TestCase.assertFalse;

import org.masch.exercise.stock.StockDataTest;
import org.masch.exercise.stock.dto.UserAcquisitionDTO;
import org.masch.exercise.stock.dto.CompanyStockActionDTO;
import org.masch.exercise.stock.strategy.ComparatorStrategyService;
import org.masch.exercise.stock.factory.StockMarketStrategyFactory;
import org.masch.exercise.stock.enums.StockActionMarketStrategyEnum;
import org.masch.exercise.stock.strategy.StockMarketStrategyService;
import org.masch.exercise.stock.dto.StockMarketStrategyComparatorResult;

public class StockStrategyComparatorTest extends StockDataTest {

    private StockMarketStrategyService firstStockMarketStrategyService;
    private StockMarketStrategyService secondStockMarketStrategyService;
    private ComparatorStrategyService comparatorStrategyService = new ComparatorStrategyService();
    private StockMarketStrategyFactory stockMarketStrategyFactory = new StockMarketStrategyFactory();

    @Before
    public void init() {
        super.init();

        this.firstStockMarketStrategyService = stockMarketStrategyFactory.getStockMarketStrategy(StockActionMarketStrategyEnum.FIRST_STRATEGY);
        this.secondStockMarketStrategyService = stockMarketStrategyFactory.getStockMarketStrategy(StockActionMarketStrategyEnum.SECOND_STRATEGY);

    }

    @Test
    public void checkFirstBestStrategy() {

        CompanyStockActionDTO company = this.getYPFCompany();
        UserAcquisitionDTO userAcquisitionDTO = this.createUserAcquisitionDTO(5, 5000,"31/10/2016" );

        StockMarketStrategyComparatorResult compareResult = this.comparatorStrategyService.compare(this.firstStockMarketStrategyService, this.secondStockMarketStrategyService, company, userAcquisitionDTO);

        assertNotNull(compareResult);
        assertEquals(StockActionMarketStrategyEnum.FIRST_STRATEGY, compareResult.getStockActionMarketStrategyEnum());
        assertFalse(compareResult.getUserAcquisitionDTOList().isEmpty());
        assertEquals(10, compareResult.getUserAcquisitionDTOList().size());
        assertEquals(5000, compareResult.getInitialMoney(), 0);
        assertEquals(6696, compareResult.getFinalMoney(), 0);

    }

    @Test
    public void checkSecondBestStrategy() {

        CompanyStockActionDTO company = this.getGGALCompany();
        UserAcquisitionDTO userAcquisitionDTO = this.createUserAcquisitionDTO(5, 5000,"31/10/2016" );

        StockMarketStrategyComparatorResult compareResult = this.comparatorStrategyService.compare(this.firstStockMarketStrategyService, this.secondStockMarketStrategyService, company, userAcquisitionDTO);

        assertNotNull(compareResult);
        assertEquals(StockActionMarketStrategyEnum.SECOND_STRATEGY, compareResult.getStockActionMarketStrategyEnum());
        assertFalse(compareResult.getUserAcquisitionDTOList().isEmpty());
        assertEquals(140, compareResult.getUserAcquisitionDTOList().size());
        assertEquals(5000, compareResult.getInitialMoney(), 0);
        assertEquals(9477.26, compareResult.getFinalMoney(), 0);

    }

}
