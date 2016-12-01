package org.masch.exercise.stock.service;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

import org.masch.exercise.stock.StockDataTest;
import org.masch.exercise.stock.util.DateUtils;
import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.CompanyStockActionDTO;

public class StockExchangeServiceTest extends StockDataTest {

    @Before
    public void init() {
        super.init();

        new DateUtils();
    }

    @Test
    public void verifyStockCompanyCreation() {

        TestCase.assertFalse(this.getStockExchangeService().getList().isEmpty());
        Assert.assertEquals(3, this.getStockExchangeService().getList().size());

    }

    @Test
    public void findExistingCompanyName() {

        CompanyStockActionDTO companyStockActionDTO = this.getYPFCompany();
        assertNotNull(companyStockActionDTO);
        assertEquals(11, companyStockActionDTO.getStocksActionsList().size());
    }

    @Test
    public void findNotExistingCompanyName() {

        TestCase.assertNull(this.getStockExchangeService().findCompanyByName("IP"));

    }

    @Test
    public void checkAverageStockValueUntilDate() {

        CompanyStockActionDTO companyStockActionDTO = this.getYPFCompany();

        Assert.assertEquals(291, this.getStockExchangeService().getAverageValue(this.newDate("4/11/2016"), companyStockActionDTO.getStocksActionsList()), 0.01);
    }

    @Test
    public void checkBuyStockAction() {

        CompanyStockActionDTO companyStockActionDTO = this.getYPFCompany();

        List<StockActionDTO> stockActionDTOBought = this.getStockExchangeService().buyStockActions(1000, companyStockActionDTO.getStocksActionsList());
        assertNotNull(stockActionDTOBought);
        assertFalse(stockActionDTOBought.isEmpty());
        assertEquals(3, stockActionDTOBought.size());

    }

    @Test
    public void checkBuyStockActionWithoutMoney() {

        CompanyStockActionDTO companyStockActionDTO = this.getYPFCompany();

        List<StockActionDTO> stockActionDTOBought = this.getStockExchangeService().buyStockActions(0, companyStockActionDTO.getStocksActionsList());
        assertNotNull(stockActionDTOBought);
        assertTrue(stockActionDTOBought.isEmpty());

    }

    @Test
    public void sellStockActions() {

        List<StockActionDTO> stocksActionsList = this.getYPFCompany().getStocksActionsList();

        double value = this.getStockExchangeService().getStockActionsValue(stocksActionsList);
        assertEquals(3286, value, 0);

    }

    @Test
    public void verifyLastDayOfTheMonth() {

        assertTrue(DateUtils.isLastDayOfTheMonth(this.newDate("29/02/2016")));
        assertTrue(DateUtils.isLastDayOfTheMonth(this.newDate("30/11/2016")));

    }

    @Test
    public void notVerifyLastDayOfTheMonth() {

        assertFalse(DateUtils.isLastDayOfTheMonth(this.newDate("29/11/2016")));
        assertFalse(DateUtils.isLastDayOfTheMonth(this.newDate("01/11/2016")));

    }

    @Test
    public void sellStockActionsLastDayOfTheMonth() {

        Assert.assertEquals(3286, this.getStockExchangeService().getStockActionsValue(getYPFCompany().getStocksActionsList(), this.newDate("29/02/2012")), 0);

    }

    @Test
    public void notSellStockActionsLastDayOfTheMonth() {

        Assert.assertEquals(0, this.getStockExchangeService().getStockActionsValue(getYPFCompany().getStocksActionsList(), this.newDate("28/02/2016")), 0);
        Assert.assertEquals(0, this.getStockExchangeService().getStockActionsValue(getYPFCompany().getStocksActionsList(), this.newDate("15/02/2016")), 0);

    }

}
