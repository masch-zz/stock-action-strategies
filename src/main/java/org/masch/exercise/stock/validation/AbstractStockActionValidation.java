package org.masch.exercise.stock.validation;

import org.masch.exercise.stock.enums.StockActionValidationEnum;

public abstract class AbstractStockActionValidation implements StockActionValidation {

    private StockActionValidationEnum stockActionValidationEnum;

    public AbstractStockActionValidation(StockActionValidationEnum stockActionValidationEnum) {
        this.stockActionValidationEnum = stockActionValidationEnum;
    }

    public StockActionValidationEnum getStockActionValidationEnum() {
        return this.stockActionValidationEnum;
    }

}
