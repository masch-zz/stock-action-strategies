package org.masch.exercise.stock.validation;

import org.masch.exercise.stock.enums.StockActionValidationEnum;

public abstract class AbstractPercentageDifferenceValueStockActionValidation extends AbstractStockActionValidation {

    protected AbstractPercentageDifferenceValueStockActionValidation(StockActionValidationEnum stockActionValidationEnum) {
        super(stockActionValidationEnum);
    }

    protected double calculatePercentageDifference(double oldValue, double newValue) {
        return (((newValue/ oldValue) - 1 ) * 100);
    }

}
