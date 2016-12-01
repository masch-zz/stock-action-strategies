package org.masch.exercise.stock;

import java.util.Date;
import java.util.List;
import org.junit.Before;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.UserAcquisitionDTO;
import org.masch.exercise.stock.dto.StockActionMarketDTO;
import org.masch.exercise.stock.dto.CompanyStockActionDTO;
import org.masch.exercise.stock.company.service.StockExchangeService;

public class StockDataTest {

    private final static String TS_COMPANY_NAME = "TS";
    private final static String YPF_COMPANY_NAME = "YPF";
    private final static String GGAL_COMPANY_NAME = "GGAL";
    private final static String DATE_FORMAT = "dd/MM/yyyy";
    private final StockExchangeService stockExchangeService = new StockExchangeService();

    @Before
    public void init() {

        createCompanies();

    }

    protected  Date newDate(String date)  {

        try {

            return new SimpleDateFormat(DATE_FORMAT).parse(date);

        } catch (ParseException e) {

            assertTrue(String.format("Invalidate date format(%s): %s", DATE_FORMAT, date) , false);
            return null;

        }

    }

    private CompanyStockActionDTO createCompanyStockActionDTO(String name, List<StockActionDTO> stockActionDTOList, StockActionMarketDTO stockActionMarketDTO) {

        return new CompanyStockActionDTO(name, stockActionDTOList, stockActionMarketDTO);

    }

    protected  StockExchangeService getStockExchangeService() {

        return this.stockExchangeService;

    }

    protected StockActionDTO createStockActionDTO(Date date, double value) {

        StockActionDTO stockActionDTO = new StockActionDTO();

        stockActionDTO.setDate(date);
        stockActionDTO.setValue(value);

        return stockActionDTO;
    }

    private CompanyStockActionDTO createGGALCompanyStockActionDTO()  {
        List<StockActionDTO> stockActionList = new ArrayList<>();

        stockActionList.add(createStockActionDTO(newDate("1/1/2016"), 990.20));
        stockActionList.add(createStockActionDTO(newDate("2/1/2016"), 980.50));
        stockActionList.add(createStockActionDTO(newDate("3/1/2016"), 970.89));
        stockActionList.add(createStockActionDTO(newDate("4/1/2016"), 960));
        stockActionList.add(createStockActionDTO(newDate("5/1/2016"), 951.95));
        stockActionList.add(createStockActionDTO(newDate("6/1/2016"), 942.62));
        stockActionList.add(createStockActionDTO(newDate("7/1/2016"), 933.38));
        stockActionList.add(createStockActionDTO(newDate("8/1/2016"), 924.24));
        stockActionList.add(createStockActionDTO(newDate("9/1/2016"), 915.18));
        stockActionList.add(createStockActionDTO(newDate("10/1/2016"), 906.21));
        stockActionList.add(createStockActionDTO(newDate("11/1/2016"), 897.33));
        stockActionList.add(createStockActionDTO(newDate("12/1/2016"), 888.54));
        stockActionList.add(createStockActionDTO(newDate("13/1/2016"), 879.83));
        stockActionList.add(createStockActionDTO(newDate("14/1/2016"), 871.21));
        stockActionList.add(createStockActionDTO(newDate("15/1/2016"), 862.67));
        stockActionList.add(createStockActionDTO(newDate("16/1/2016"), 854.21));
        stockActionList.add(createStockActionDTO(newDate("17/1/2016"), 845.84));
        stockActionList.add(createStockActionDTO(newDate("18/1/2016"), 837.55));
        stockActionList.add(createStockActionDTO(newDate("19/1/2016"), 829.35));
        stockActionList.add(createStockActionDTO(newDate("20/1/2016"), 821.22));
        stockActionList.add(createStockActionDTO(newDate("21/1/2016"), 813.17));
        stockActionList.add(createStockActionDTO(newDate("22/1/2016"), 805.20));
        stockActionList.add(createStockActionDTO(newDate("23/1/2016"), 797.31));
        stockActionList.add(createStockActionDTO(newDate("24/1/2016"), 789.50));
        stockActionList.add(createStockActionDTO(newDate("25/1/2016"), 781.76));
        stockActionList.add(createStockActionDTO(newDate("26/1/2016"), 774.10));
        stockActionList.add(createStockActionDTO(newDate("27/1/2016"), 766.51));
        stockActionList.add(createStockActionDTO(newDate("28/1/2016"), 759.00));
        stockActionList.add(createStockActionDTO(newDate("29/1/2016"), 751.56));
        stockActionList.add(createStockActionDTO(newDate("1/2/2016"), 744.20));
        stockActionList.add(createStockActionDTO(newDate("2/2/2016"), 736.90));
        stockActionList.add(createStockActionDTO(newDate("3/2/2016"), 729.68));
        stockActionList.add(createStockActionDTO(newDate("4/2/2016"), 722.53));
        stockActionList.add(createStockActionDTO(newDate("5/2/2016"), 715.45));
        stockActionList.add(createStockActionDTO(newDate("6/2/2016"), 708.44));
        stockActionList.add(createStockActionDTO(newDate("7/2/2016"), 701.50));
        stockActionList.add(createStockActionDTO(newDate("8/2/2016"), 694.62));
        stockActionList.add(createStockActionDTO(newDate("9/2/2016"), 687.81));
        stockActionList.add(createStockActionDTO(newDate("10/2/2016"), 681.07));
        stockActionList.add(createStockActionDTO(newDate("11/2/2016"), 674.40));
        stockActionList.add(createStockActionDTO(newDate("12/2/2016"), 667.79));
        stockActionList.add(createStockActionDTO(newDate("13/2/2016"), 661.25));
        stockActionList.add(createStockActionDTO(newDate("14/2/2016"), 654.77));
        stockActionList.add(createStockActionDTO(newDate("15/2/2016"), 648.35));
        stockActionList.add(createStockActionDTO(newDate("16/2/2016"), 641.99));
        stockActionList.add(createStockActionDTO(newDate("17/2/2016"), 635.70));
        stockActionList.add(createStockActionDTO(newDate("18/2/2016"), 629.47));
        stockActionList.add(createStockActionDTO(newDate("19/2/2016"), 623.30));
        stockActionList.add(createStockActionDTO(newDate("20/2/2016"), 617.20));
        stockActionList.add(createStockActionDTO(newDate("21/2/2016"), 611.15));
        stockActionList.add(createStockActionDTO(newDate("22/2/2016"), 605.16));
        stockActionList.add(createStockActionDTO(newDate("23/2/2016"), 599.23));
        stockActionList.add(createStockActionDTO(newDate("24/2/2016"), 593.36));
        stockActionList.add(createStockActionDTO(newDate("25/2/2016"), 587.54));
        stockActionList.add(createStockActionDTO(newDate("26/2/2016"), 581.78));
        stockActionList.add(createStockActionDTO(newDate("27/2/2016"), 576.08));
        stockActionList.add(createStockActionDTO(newDate("28/2/2016"), 570.44));
        stockActionList.add(createStockActionDTO(newDate("29/2/2016"), 564.85));
        stockActionList.add(createStockActionDTO(newDate("1/3/2016"), 559.31));
        stockActionList.add(createStockActionDTO(newDate("2/3/2016"), 553.83));
        stockActionList.add(createStockActionDTO(newDate("3/3/2016"), 548.40));
        stockActionList.add(createStockActionDTO(newDate("4/3/2016"), 543.03));
        stockActionList.add(createStockActionDTO(newDate("5/3/2016"), 537.71));
        stockActionList.add(createStockActionDTO(newDate("6/3/2016"), 532.44));
        stockActionList.add(createStockActionDTO(newDate("7/3/2016"), 527.22));
        stockActionList.add(createStockActionDTO(newDate("8/3/2016"), 522.05));
        stockActionList.add(createStockActionDTO(newDate("9/3/2016"), 516.93));
        stockActionList.add(createStockActionDTO(newDate("10/3/2016"), 511.87));
        stockActionList.add(createStockActionDTO(newDate("11/3/2016"), 506.85));
        stockActionList.add(createStockActionDTO(newDate("12/3/2016"), 501.89));
        stockActionList.add(createStockActionDTO(newDate("13/3/2016"), 496.97));
        stockActionList.add(createStockActionDTO(newDate("14/3/2016"), 492.10));
        stockActionList.add(createStockActionDTO(newDate("15/3/2016"), 487.27));
        stockActionList.add(createStockActionDTO(newDate("16/3/2016"), 482.50));
        stockActionList.add(createStockActionDTO(newDate("17/3/2016"), 477.77));
        stockActionList.add(createStockActionDTO(newDate("18/3/2016"), 473.09));
        stockActionList.add(createStockActionDTO(newDate("19/3/2016"), 468.45));
        stockActionList.add(createStockActionDTO(newDate("20/3/2016"), 463.86));
        stockActionList.add(createStockActionDTO(newDate("21/3/2016"), 459.32));
        stockActionList.add(createStockActionDTO(newDate("22/3/2016"), 454.81));
        stockActionList.add(createStockActionDTO(newDate("23/3/2016"), 450.36));
        stockActionList.add(createStockActionDTO(newDate("24/3/2016"), 445.94));
        stockActionList.add(createStockActionDTO(newDate("25/3/2016"), 441.57));
        stockActionList.add(createStockActionDTO(newDate("26/3/2016"), 437.25));
        stockActionList.add(createStockActionDTO(newDate("27/3/2016"), 432.96));
        stockActionList.add(createStockActionDTO(newDate("28/3/2016"), 428.72));
        stockActionList.add(createStockActionDTO(newDate("29/3/2016"), 424.52));
        stockActionList.add(createStockActionDTO(newDate("1/4/2016"), 420.36));
        stockActionList.add(createStockActionDTO(newDate("2/4/2016"), 416.24));
        stockActionList.add(createStockActionDTO(newDate("3/4/2016"), 412.16));
        stockActionList.add(createStockActionDTO(newDate("4/4/2016"), 408.12));
        stockActionList.add(createStockActionDTO(newDate("5/4/2016"), 404.12));
        stockActionList.add(createStockActionDTO(newDate("6/4/2016"), 400.16));
        stockActionList.add(createStockActionDTO(newDate("7/4/2016"), 396.24));
        stockActionList.add(createStockActionDTO(newDate("8/4/2016"), 392.35));
        stockActionList.add(createStockActionDTO(newDate("9/4/2016"), 388.51));
        stockActionList.add(createStockActionDTO(newDate("10/4/2016"), 384.70));
        stockActionList.add(createStockActionDTO(newDate("11/4/2016"), 380.93));
        stockActionList.add(createStockActionDTO(newDate("12/4/2016"), 377.20));
        stockActionList.add(createStockActionDTO(newDate("13/4/2016"), 373.50));
        stockActionList.add(createStockActionDTO(newDate("14/4/2016"), 369.84));
        stockActionList.add(createStockActionDTO(newDate("15/4/2016"), 366.22));
        stockActionList.add(createStockActionDTO(newDate("16/4/2016"), 362.63));
        stockActionList.add(createStockActionDTO(newDate("17/4/2016"), 359.07));
        stockActionList.add(createStockActionDTO(newDate("18/4/2016"), 355.56));
        stockActionList.add(createStockActionDTO(newDate("19/4/2016"), 352.07));
        stockActionList.add(createStockActionDTO(newDate("20/4/2016"), 348.62));
        stockActionList.add(createStockActionDTO(newDate("21/4/2016"), 345.20));
        stockActionList.add(createStockActionDTO(newDate("22/4/2016"), 341.82));
        stockActionList.add(createStockActionDTO(newDate("23/4/2016"), 338.47));
        stockActionList.add(createStockActionDTO(newDate("24/4/2016"), 335.15));
        stockActionList.add(createStockActionDTO(newDate("25/4/2016"), 331.87));
        stockActionList.add(createStockActionDTO(newDate("26/4/2016"), 328.62));
        stockActionList.add(createStockActionDTO(newDate("27/4/2016"), 325.40));
        stockActionList.add(createStockActionDTO(newDate("28/4/2016"), 322.21));
        stockActionList.add(createStockActionDTO(newDate("29/4/2016"), 319.05));
        stockActionList.add(createStockActionDTO(newDate("1/5/2016"), 315.92));
        stockActionList.add(createStockActionDTO(newDate("2/5/2016"), 312.83));
        stockActionList.add(createStockActionDTO(newDate("3/5/2016"), 309.76));
        stockActionList.add(createStockActionDTO(newDate("4/5/2016"), 306.73));
        stockActionList.add(createStockActionDTO(newDate("5/5/2016"), 303.72));
        stockActionList.add(createStockActionDTO(newDate("6/5/2016"), 300.74));
        stockActionList.add(createStockActionDTO(newDate("7/5/2016"), 297.80));
        stockActionList.add(createStockActionDTO(newDate("8/5/2016"), 294.88));
        stockActionList.add(createStockActionDTO(newDate("9/5/2016"), 291.99));
        stockActionList.add(createStockActionDTO(newDate("10/5/2016"), 289.13));
        stockActionList.add(createStockActionDTO(newDate("11/5/2016"), 286.29));
        stockActionList.add(createStockActionDTO(newDate("12/5/2016"), 283.49));
        stockActionList.add(createStockActionDTO(newDate("13/5/2016"), 280.71));
        stockActionList.add(createStockActionDTO(newDate("14/5/2016"), 277.96));
        stockActionList.add(createStockActionDTO(newDate("15/5/2016"), 275.23));
        stockActionList.add(createStockActionDTO(newDate("16/5/2016"), 272.54));
        stockActionList.add(createStockActionDTO(newDate("17/5/2016"), 269.87));
        stockActionList.add(createStockActionDTO(newDate("18/5/2016"), 267.22));
        stockActionList.add(createStockActionDTO(newDate("19/5/2016"), 264.60));
        stockActionList.add(createStockActionDTO(newDate("20/5/2016"), 262.01));
        stockActionList.add(createStockActionDTO(newDate("21/5/2016"), 259.44));
        stockActionList.add(createStockActionDTO(newDate("22/5/2016"), 256.90));
        stockActionList.add(createStockActionDTO(newDate("23/5/2016"), 254.38));
        stockActionList.add(createStockActionDTO(newDate("24/5/2016"), 251.89));
        stockActionList.add(createStockActionDTO(newDate("25/5/2016"), 249.42));

        return createCompanyStockActionDTO(GGAL_COMPANY_NAME, stockActionList, getDefaultCompanyActionMarketDTO());
    }

    private CompanyStockActionDTO createYPFCompanyStockActionDTO()  {

        List<StockActionDTO> stockActionList = new ArrayList<>();

        stockActionList.add(createStockActionDTO(newDate("1/11/2016"), 290));
        stockActionList.add(createStockActionDTO(newDate("2/11/2016"), 294));  //  1.37
        stockActionList.add(createStockActionDTO(newDate("3/11/2016"), 288));  // -2.04
        stockActionList.add(createStockActionDTO(newDate("4/11/2016"), 292));  //  1.39
        stockActionList.add(createStockActionDTO(newDate("5/11/2016"), 297));  //  1.71
        stockActionList.add(createStockActionDTO(newDate("6/11/2016"), 296));  // -0.34
        stockActionList.add(createStockActionDTO(newDate("7/11/2016"), 299));  //  1.01
        stockActionList.add(createStockActionDTO(newDate("8/11/2016"), 300));  //  0.33
        stockActionList.add(createStockActionDTO(newDate("9/11/2016"), 300));  //  0
        stockActionList.add(createStockActionDTO(newDate("10/11/2016"), 320)); //  6.67
        stockActionList.add(createStockActionDTO(newDate("11/11/2016"), 310)); // -3.13

        return createCompanyStockActionDTO(YPF_COMPANY_NAME, stockActionList, getDefaultCompanyActionMarketDTO());
    }

    private CompanyStockActionDTO createTSCompanyStockActionDTO()  {

        List<StockActionDTO> stockActionList = new ArrayList<>();

        stockActionList.add(createStockActionDTO(newDate("20/11/2016"), 290));
        stockActionList.add(createStockActionDTO(newDate("21/11/2016"), 294));
        stockActionList.add(createStockActionDTO(newDate("22/11/2016"), 288));
        stockActionList.add(createStockActionDTO(newDate("23/11/2016"), 292));
        stockActionList.add(createStockActionDTO(newDate("24/11/2016"), 297));
        stockActionList.add(createStockActionDTO(newDate("25/11/2016"), 296));
        stockActionList.add(createStockActionDTO(newDate("26/11/2016"), 299));
        stockActionList.add(createStockActionDTO(newDate("27/11/2016"), 300));
        stockActionList.add(createStockActionDTO(newDate("28/11/2016"), 320));
        stockActionList.add(createStockActionDTO(newDate("29/11/2016"), 310));
        stockActionList.add(createStockActionDTO(newDate("30/11/2016"), 330));
        stockActionList.add(createStockActionDTO(newDate("01/12/2016"), 50));
        stockActionList.add(createStockActionDTO(newDate("02/12/2016"), 1310));


        return createCompanyStockActionDTO(TS_COMPANY_NAME, stockActionList, getDefaultCompanyActionMarketDTO());
    }

    protected StockActionMarketDTO getDefaultCompanyActionMarketDTO() {
        StockActionMarketDTO result = new StockActionMarketDTO();

        result.setCount(100);

        return result;
    }

    private void createCompanies() {

        this.stockExchangeService.add(createTSCompanyStockActionDTO());
        this.stockExchangeService.add(createYPFCompanyStockActionDTO());
        this.stockExchangeService.add(createGGALCompanyStockActionDTO());

    }

    protected  CompanyStockActionDTO getYPFCompany() {

        return this.stockExchangeService.findCompanyByName(YPF_COMPANY_NAME);

    }

    protected  CompanyStockActionDTO getGGALCompany() {

        return this.stockExchangeService.findCompanyByName(GGAL_COMPANY_NAME);

    }

    protected UserAcquisitionDTO createUserAcquisitionDTO(int actionCount, double initialMoney, String stockActionBuyDate) {

        StockActionMarketDTO stockActionUserDTO = new StockActionMarketDTO();
        stockActionUserDTO.setCount(actionCount);
        stockActionUserDTO.setBuyDate(newDate(stockActionBuyDate));

        UserAcquisitionDTO userAcquisitionDTO = new UserAcquisitionDTO();
        userAcquisitionDTO.setMoney(initialMoney);
        userAcquisitionDTO.setStockActionMarketDTO(stockActionUserDTO);


        return userAcquisitionDTO;
    }

    protected  void assertUserAcquisitionEquals(int actionCount, double userMoney, UserAcquisitionDTO userAcquisitionDTO) {

        assertNotNull(userAcquisitionDTO);
        assertNotNull(userAcquisitionDTO.getStockActionMarketDTO());
        assertEquals(userMoney, userAcquisitionDTO.getMoney(), 0.001);
        assertEquals(actionCount, userAcquisitionDTO.getStockActionMarketDTO().getCount());

    }

    void printPercentageDifferences(double initialValue, double topValue, double percentageDifference) {

        int day = 1;
        int month = 1;
        double newValue = initialValue;
        double oldValue = initialValue;

        while (newValue > topValue) {
            newValue = ((percentageDifference / 100) + 1) * oldValue;

            DecimalFormat df=new DecimalFormat("0.00");
            String formattedDay = df.format(newValue);

            System.out.println("stockActionList.add(createStockActionDTO(newDate(\"" + day + "/" + month + "/2016\"), " + formattedDay + "));");

            oldValue = newValue;
            day++;
            if (day == 30) {
                day = 1;
                month++;
            }
        }

    }

}
