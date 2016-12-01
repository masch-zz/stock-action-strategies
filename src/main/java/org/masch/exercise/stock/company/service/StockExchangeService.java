package org.masch.exercise.stock.company.service;

import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.masch.exercise.stock.util.DateUtils;
import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.StockActionMarketDTO;
import org.masch.exercise.stock.dto.CompanyStockActionDTO;

public class StockExchangeService {

    final private Map<String, CompanyStockActionDTO> companyStockActionList = new HashMap<>();

    public void add(CompanyStockActionDTO companyStockActionDTO) {
        this.companyStockActionList.put(companyStockActionDTO.getName(), companyStockActionDTO);
    }

    public Collection<CompanyStockActionDTO> getList() {
        return this.companyStockActionList.values();
    }

    public CompanyStockActionDTO findCompanyByName(String name) {

        return this.companyStockActionList.get(name);

    }

    public double getAverageValue(Date untilDate, List<StockActionDTO> stocksActionsList) {
        return stocksActionsList.stream()
                .filter(p -> p.getDate().before(untilDate) || p.getDate().equals(untilDate))
                .collect(Collectors.averagingDouble(StockActionDTO::getValue));
    }

    public StockActionMarketDTO buyStockActions(double moneyInversion, double actionValue, Date buyDate, int maxActionCount) {

        int stockActionBoughtCount = 0;
        double currentAmount = moneyInversion;
        StockActionMarketDTO result = new StockActionMarketDTO();
        boolean hasEnoughMoney = currentAmount >= actionValue;

        for (int i = 0; hasEnoughMoney && i < maxActionCount; i++) {
            currentAmount -= actionValue;
            ++stockActionBoughtCount;
            hasEnoughMoney = currentAmount >= actionValue;
        }

        result.setBuyDate(buyDate);
        result.setCount(stockActionBoughtCount);

        return result;

    }

    public List<StockActionDTO> buyStockActions(double maxAmount, List<StockActionDTO> stockActionDTOList) {

        boolean hasEnoughMoney = true;
        double currentAmount = maxAmount;
        List<StockActionDTO> result = new ArrayList<>();
        Iterator<StockActionDTO> stockActionDTOIterator = stockActionDTOList.iterator();

        while (stockActionDTOIterator.hasNext() && hasEnoughMoney)  {

            StockActionDTO stockActionDTO = stockActionDTOIterator.next();
            hasEnoughMoney = (currentAmount - stockActionDTO.getValue() > 0);
            if (hasEnoughMoney) {
                result.add(stockActionDTO);
                currentAmount -= stockActionDTO.getValue();
            }
        }

        return result;
    }

    public double getStockActionsValue(List<StockActionDTO> stocksActionsList) {

        return stocksActionsList.stream()
                .mapToDouble(StockActionDTO::getValue)
                .sum();
    }

    public double getStockActionsValue(List<StockActionDTO> stocksActionsList, Date date) {

        if (DateUtils.isLastDayOfTheMonth(date)) {
            return getStockActionsValue(stocksActionsList);
        }

        return 0;
    }

}
