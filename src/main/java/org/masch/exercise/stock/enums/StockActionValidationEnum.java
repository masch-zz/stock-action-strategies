package org.masch.exercise.stock.enums;

public enum StockActionValidationEnum {

    FIRST_BUY("First buy"),
    FIRST_SELL("First sell"),
    SECOND_BUY("Second buy"),
    SECOND_SELL("Second sell"),
    LAST_DAY_MONTH_SELL("Last day month sell"),
    ;

    private String name;

    StockActionValidationEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
