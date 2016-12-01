package org.masch.exercise.stock.dto;

import java.util.List;

public class CompanyStockActionDTO extends CompanyDTO {

    final private List<StockActionDTO> stocksActionsList;
    final private StockActionMarketDTO stockActionMarketDTO;

    public CompanyStockActionDTO(String name, List<StockActionDTO> stocksActionsList, StockActionMarketDTO stockActionMarketDTO) {
        setName(name);
        this.stocksActionsList = stocksActionsList;
        this.stockActionMarketDTO = stockActionMarketDTO;
    }

    public List<StockActionDTO> getStocksActionsList() {
        return stocksActionsList;
    }

    public StockActionMarketDTO getStockActionMarketDTO() {
        return stockActionMarketDTO;
    }

}
